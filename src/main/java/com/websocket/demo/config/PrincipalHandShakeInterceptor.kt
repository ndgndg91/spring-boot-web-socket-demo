package com.websocket.demo.config

import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.server.ServerHttpRequest
import org.springframework.http.server.ServerHttpResponse
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.HandshakeInterceptor
import java.lang.Exception

class PrincipalHandShakeInterceptor: HandshakeInterceptor {
    companion object {
        private val logger = LoggerFactory.getLogger(PrincipalHandShakeInterceptor::class.java)
    }

    override fun beforeHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Boolean {
        logger.info("before hand")
        return request.headers[HttpHeaders.AUTHORIZATION] != null
    }

    override fun afterHandshake(
        request: ServerHttpRequest,
        response: ServerHttpResponse,
        wsHandler: WebSocketHandler,
        exception: Exception?
    ) {
        logger.info("after hand")
    }
}