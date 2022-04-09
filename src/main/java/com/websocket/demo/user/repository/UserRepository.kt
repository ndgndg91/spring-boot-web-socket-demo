package com.websocket.demo.user.repository

import com.websocket.demo.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, String>{
    fun findByUsername(username: String): User?
}