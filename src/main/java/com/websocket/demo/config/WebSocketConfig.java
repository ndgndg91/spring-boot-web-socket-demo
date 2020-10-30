package com.websocket.demo.config;

import com.websocket.demo.handler.ChatWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    private static final String CHAT_END_POINT = "/chat";

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getChatWebSocketHandler(), CHAT_END_POINT)
                .setAllowedOrigins("*");
    }

    @Bean
    public ChatWebSocketHandler getChatWebSocketHandler(){
        return new ChatWebSocketHandler();
    }
}
