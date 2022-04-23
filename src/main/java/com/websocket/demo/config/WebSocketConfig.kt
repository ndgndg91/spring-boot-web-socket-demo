package com.websocket.demo.config

import com.websocket.demo.handler.ChatWebSocketHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@EnableWebSocket
@Configuration
class WebSocketConfig(private val chatWebSocketHandler: ChatWebSocketHandler): WebSocketConfigurer {
    companion object {
        private const val CHAT_END_POINT = "/chat"
    }

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(chatWebSocketHandler, CHAT_END_POINT)
            .setHandshakeHandler(PrincipalHandShakeHandler())
            .setAllowedOrigins("*")
    }
}