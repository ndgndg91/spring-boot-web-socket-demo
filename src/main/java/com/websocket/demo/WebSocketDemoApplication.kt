package com.websocket.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication(exclude = [UserDetailsServiceAutoConfiguration::class])
class WebSocketDemoApplication

fun main(args: Array<String>) {
    SpringApplication.run(WebSocketDemoApplication::class.java, *args)
}