package com.websocket.demo.chathistory

import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "chat_room_history")
class ChatMessageHistory(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var userId: Long,
    var roomId: Long,
    var writer: String,
    var contents: String,
    var createdAt: LocalDateTime
) {
    constructor(): this(0, 0, 0, "", "", LocalDateTime.now())

    constructor(userId: Long, roomId: Long, writer: String, contents: String): this(null, userId, roomId, writer, contents, LocalDateTime.now())
}