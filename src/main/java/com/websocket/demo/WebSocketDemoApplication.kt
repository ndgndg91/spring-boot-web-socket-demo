package com.websocket.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class WebSocketDemoApplication

fun main(args: Array<String>) {
    SpringApplication.run(WebSocketDemoApplication::class.java, *args)
}