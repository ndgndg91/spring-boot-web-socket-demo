package com.websocket.demo.handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.websocket.demo.test.TestMessage
import com.websocket.demo.test.TestRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
class MyTextWebSocketHandler(
    private val om: ObjectMapper,
    private val repository: TestRepository
): TextWebSocketHandler() {

    private val log = LoggerFactory.getLogger(MyTextWebSocketHandler::class.java)

    override fun afterConnectionEstablished(session: WebSocketSession) {
        log.info("afterConnectionEstablished")
        super.afterConnectionEstablished(session)
    }

    override fun handleMessage(session: WebSocketSession, message: WebSocketMessage<*>) {
        val from = message.payload.toString()
        log.info("handleMessage : {}", from)
        val testMessage = repository.findByValid(true).let {
            TestMessage(
                it.message,
                it.valid,
                it.timestamp.toBigInteger()
            )
        }

        session.sendMessage(TextMessage(om.writeValueAsBytes(testMessage)))
    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        log.info("afterConnectionClosed")
        super.afterConnectionClosed(session, status)
    }
}