package com.websocket.demo.handler

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

// TODO chat room repository 구현
class ChatWebSocketHandler : TextWebSocketHandler() {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(ChatWebSocketHandler::class.java)
    }
    private val webSocketSessions: MutableList<WebSocketSession> = ArrayList()

    @Throws(Exception::class)
    override fun afterConnectionEstablished(session: WebSocketSession) {
        log.info("{}", session)
        webSocketSessions.add(session)
        super.afterConnectionEstablished(session)
    }

    @Throws(Exception::class)
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        log.info("{} : {}", session, message)
        log.info("{}", message.payload)
        for (webSocketSession in webSocketSessions) {
            webSocketSession.sendMessage(message)
        }
    }

    @Throws(Exception::class)
    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        log.info("{}", session)
        webSocketSessions.remove(session)
        super.afterConnectionClosed(session, status)
    }
}