package com.websocket.demo.room

import org.springframework.data.redis.core.RedisHash
import java.time.LocalDateTime

@RedisHash("rooms")
data class ChatRoom(var id: String,
                    var title: String?,
                    var lastModifiedAt: LocalDateTime?,
                    var createdAt: LocalDateTime?
)