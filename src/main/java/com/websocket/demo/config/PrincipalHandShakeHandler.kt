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
        return try {
            val accessToken = request.headers[HttpHeaders.AUTHORIZATION]?.first().toAccessToken()
            JwtHelper.toUser(accessToken)
        } catch (e: NoSuchElementException) {
            return null
        }
    }
}

private fun String?.toAccessToken(): String? {
    if (this == null) return null
    return try {
        Regex("[B|b]earer (.+)").find(this)?.groupValues?.get(1)
    } catch (e: NullPointerException) {
        null
    }
}
