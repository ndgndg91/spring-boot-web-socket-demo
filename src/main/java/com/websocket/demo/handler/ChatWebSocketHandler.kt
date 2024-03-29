package com.websocket.demo.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.websocket.demo.chathistory.service.ChatMessageHistoryService
import com.websocket.demo.chatroom.LiveChatRoom
import com.websocket.demo.chatroom.repository.ChatRoomRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.util.concurrent.ConcurrentHashMap

@Component
class ChatWebSocketHandler(
    private val chatRoomRepository: ChatRoomRepository, private val om: ObjectMapper,
    private val publisher: ApplicationEventPublisher,
    private val chatMessageHistoryService: ChatMessageHistoryService
) : TextWebSocketHandler() {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(ChatWebSocketHandler::class.java)
    }

    private val chatRooms: MutableMap<Long, LiveChatRoom> = ConcurrentHashMap<Long, LiveChatRoom> ()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        log.info("$session")
        super.afterConnectionEstablished(session)
    }

    @Throws(Exception::class)
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        log.info("{} : {}", session, message)
        log.info("{}", message.payload)
        val chatMessage = om.readValue(message.payload, ChatMessage::class.java)
        log.info("$chatMessage")

        var liveChatRoom = chatRooms[chatMessage.roomId]
        if (liveChatRoom == null) {
            val chatRoom = chatRoomRepository.findById(chatMessage.roomId).orElseThrow()
            liveChatRoom = LiveChatRoom(chatRoom.id!!, chatRoom.title)
            chatRooms[chatRoom.id!!] = liveChatRoom
        }

        liveChatRoom.connect(session)
        liveChatRoom.clean()
        liveChatRoom.chat(message)

        publisher.publishEvent(chatMessage.toChatEvent())
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        log.info("$session")
        super.afterConnectionClosed(session, status)
    }

    private data class ChatMessage(
        val userId: Long,
        val roomId: Long,
        val writer: String,
        val contents: String,
    ) {
        fun toChatEvent(): ChatEvent {
            return ChatEvent(userId, roomId, writer, contents)
        }
    }
}
