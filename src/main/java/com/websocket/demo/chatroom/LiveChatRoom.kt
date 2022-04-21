package com.websocket.demo.chatroom

import org.slf4j.LoggerFactory
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.util.*
import java.util.concurrent.ConcurrentLinkedDeque

data class LiveChatRoom(
    val id: Long,
    val title: String,
    var sessions: Deque<WebSocketSession> = ConcurrentLinkedDeque(),
    // TODO user 마다 보여지는 message 다르게 구현하기
    val liveMessages: Deque<TextMessage> = ConcurrentLinkedDeque()
) {
    companion object {
        private val log = LoggerFactory.getLogger(LiveChatRoom::class.java)
    }
    fun connect(session: WebSocketSession) {
        sessions.add(session)
        for (m in liveMessages){
            session.sendMessage(m)
        }
    }

    fun chat(message: TextMessage) {
        log.info("$liveMessages")
        for (session in sessions) {
            if (session.isOpen) {
                session.sendMessage(message)
                liveMessages.add(message)
            }
        }
    }

    fun clean() {
        this.sessions.parallelStream()
            .dropWhile {!it.isOpen}
    }

}