package com.websocket.demo.handler

data class ChatEvent(
    val userId: Long,
    val roomId: Long,
    val writer: String,
    val contents: String,
)