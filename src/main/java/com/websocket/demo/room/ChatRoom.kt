package com.websocket.demo.room

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "chat_room")
class ChatRoom(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var title: String,
    var lastModifiedAt: LocalDateTime?,
    var createdAt: LocalDateTime?
) {
    constructor(): this(0, "", null,  LocalDateTime.now())

    constructor(title: String): this(null, title, null, LocalDateTime.now())
}