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
) {
    companion object {
        private val log = LoggerFactory.getLogger(LiveChatRoom::class.java)
    }
    fun connect(session: WebSocketSession) {
        sessions.add(session)
    }

    fun chat(message: TextMessage) {
        for (session in sessions) {
            if (session.isOpen) {
                session.sendMessage(message)
            }
        }
    }

    fun clean() {
        this.sessions.parallelStream()
            .dropWhile {!it.isOpen}
    }

}