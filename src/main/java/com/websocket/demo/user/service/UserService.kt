package com.websocket.demo.user.service

import com.websocket.demo.auth.JwtHelper
import com.websocket.demo.room.exception.ServiceException
import com.websocket.demo.user.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    fun signIn(username: String, password: String): String {
        val user = userRepository.findByUsername(username) ?: throw ServiceException(
            HttpStatus.NOT_FOUND.value(),
            "can't find user by $username"
        )

        // TODO 비밀번호 확인
        return JwtHelper.createJwt(user.id, user.username)
    }
}
