package com.websocket.demo

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@SpringBootTest
internal class WebSocketDemoApplicationTests {

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Test
    fun contextLoads() {
        val encode = passwordEncoder.encode("123123")
        println(encode)
    }
}