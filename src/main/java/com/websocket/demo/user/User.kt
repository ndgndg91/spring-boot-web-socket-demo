package com.websocket.demo.user

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "chat_user")
class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    var nickname: String,
    var username: String,
    var password: String,
    var lastModifiedAt: LocalDateTime?,
    var createdAt: LocalDateTime
) {
    constructor() : this(0, "", "", "", null, LocalDateTime.now())
}