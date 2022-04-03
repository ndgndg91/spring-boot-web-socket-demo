package com.websocket.demo.room

import java.time.LocalDateTime

data class ChatRoom(var id: String,
                    var title: String?,
                    var lastModifiedAt: LocalDateTime?,
                    var createdAt: LocalDateTime?
) {

    constructor(): this("", null, null, null)
    /**
     * for redis deserialize
     */
    constructor(id: String): this(id, null, null, null)
}