package com.websocket.demo.config

import com.websocket.demo.handler.ChatWebSocketHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@EnableWebSocket
@Configuration
open class WebSocketConfig: WebSocketConfigurer {
    companion object {
        private const val CHAT_END_POINT = "/chat"
    }

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(getChatWebSocketHandler(), CHAT_END_POINT)
            .setAllowedOrigins("*")
    }

    @Bean
    open fun getChatWebSocketHandler(): ChatWebSocketHandler {
        return ChatWebSocketHandler()
    }

}