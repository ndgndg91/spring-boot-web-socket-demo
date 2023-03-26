package com.websocket.demo.test

import com.websocket.demo.protobuf.Message.TestMessage
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.concurrent.ConcurrentLinkedQueue

@Repository
class TestRepository {
    private val data = ConcurrentLinkedQueue<TestMessage>()

    init {
        val trueMessage = TestMessage.newBuilder().setMessage("hello").setValid(true)
            .setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
            .build()
        val falseMessage = TestMessage.newBuilder().setMessage("hello").setValid(true)
            .setTimestamp(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC))
            .build()
        data.add(trueMessage)
        data.add(falseMessage)
    }
    fun findByValid(valid: Boolean): TestMessage {
        return data.first { it.valid == valid }
    }
}