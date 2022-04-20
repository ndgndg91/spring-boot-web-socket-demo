package com.websocket.demo.chatroom

import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

data class LiveChatRoom(
    val id: Long,
    val title: String,
    // TODO thread safe 확인하기
    val sessions: MutableSet<WebSocketSession> = mutableSetOf()
) {
    fun connect(session: WebSocketSession) {
        sessions.add(session)
    }

    fun chat(message: TextMessage) {
        for (session in sessions) {
            session.sendMessage(message)
        }
    }

}