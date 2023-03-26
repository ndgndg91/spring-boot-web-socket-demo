package com.websocket.demo.handler

import com.websocket.demo.test.TestRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.BinaryMessage
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.BinaryWebSocketHandler

@Component
class MyBinaryWebSocketHandler(
    private val repository: TestRepository
): BinaryWebSocketHandler() {

    private val log = LoggerFactory.getLogger(MyBinaryWebSocketHandler::class.java)

    override fun afterConnectionEstablished(session: WebSocketSession) {
        log.info("afterConnectionEstablished")
        super.afterConnectionEstablished(session)
    }

    override fun handleBinaryMessage(session: WebSocketSession, message: BinaryMessage) {
        val from = String(message.payload.array())
        log.info("handleBinaryMessage : {}", from)
        session.sendMessage(BinaryMessage(repository.findByValid(true).toByteArray()))
    }

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        super.handleMessage(session, message)
    }

    override fun handleTransportError(session: WebSocketSession, exception: Throwable) {
        log.error("handleTransportError", exception)
        super.handleTransportError(session, exception)
    }


    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        log.info("afterConnectionClosed")
        super.afterConnectionClosed(session, status)
    }
}