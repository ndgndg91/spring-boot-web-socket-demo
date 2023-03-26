package com.websocket.demo.config

import com.websocket.demo.handler.ChatWebSocketHandler
import com.websocket.demo.handler.MyBinaryWebSocketHandler
import com.websocket.demo.handler.MyTextWebSocketHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@EnableWebSocket
@Configuration
class WebSocketConfig(
    private val chatWebSocketHandler: ChatWebSocketHandler,
    private val myBinaryWebSocketHandler: MyBinaryWebSocketHandler,
    private val myTextWebSocketHandler: MyTextWebSocketHandler
): WebSocketConfigurer {
    companion object {
        private const val CHAT_END_POINT = "/chat"
        private const val BINARY_END_POINT = "/binary"
        private const val TEXT_END_POINT = "/text"
    }

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(chatWebSocketHandler, CHAT_END_POINT)
            .addInterceptors(PrincipalHandShakeInterceptor())
            .setHandshakeHandler(PrincipalHandShakeHandler())
            .setAllowedOrigins("*")

        registry.addHandler(myBinaryWebSocketHandler, BINARY_END_POINT)
            .setAllowedOrigins("*")

        registry.addHandler(myTextWebSocketHandler, TEXT_END_POINT)
            .setAllowedOrigins("*")
    }
}