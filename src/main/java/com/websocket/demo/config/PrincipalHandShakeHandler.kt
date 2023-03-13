package com.websocket.demo.config

import com.websocket.demo.auth.JwtHelper
import org.slf4j.LoggerFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.server.ServerHttpRequest
import org.springframework.web.socket.WebSocketHandler
import org.springframework.web.socket.server.support.DefaultHandshakeHandler
import java.security.Principal

class PrincipalHandShakeHandler : DefaultHandshakeHandler() {
    companion object {
        private val logger = LoggerFactory.getLogger(PrincipalHandShakeHandler::class.java)
    }

    override fun determineUser(
        request: ServerHttpRequest,
        wsHandler: WebSocketHandler,
        attributes: MutableMap<String, Any>
    ): Principal? {
        logger.info("determin user")
        return try {
            val authorizationHeaderValue = request.headers[HttpHeaders.AUTHORIZATION]?.first()
            val accessToken = (Regex("[B|b]earer (.+)")).find(authorizationHeaderValue!!)?.groupValues?.get(1)
            JwtHelper.toUser(accessToken)
        } catch (e: NoSuchElementException) {
            null
        } catch (e: NullPointerException) {
            null
        }
    }
}