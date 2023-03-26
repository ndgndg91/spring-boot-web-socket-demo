package com.websocket.demo.test

import java.math.BigInteger
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.Random

data class TestMessage(
    val message: String,
    val valid: Boolean = Random().nextBoolean(),
    val timestamp: BigInteger = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC).toBigInteger()
)