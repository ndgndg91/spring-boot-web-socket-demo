package com.websocket.demo.user.service

import com.websocket.demo.auth.JwtHelper
import com.websocket.demo.extension.isNotNull
import com.websocket.demo.room.exception.ServiceException
import com.websocket.demo.user.User
import com.websocket.demo.user.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional(readOnly = true)
    open fun signIn(username: String, password: String): String {
        val user = userRepository.findByUsername(username) ?: throw ServiceException(
            HttpStatus.NOT_FOUND.value(),
            "can't find user by $username"
        )

        if (passwordEncoder.matches(password, user.password)) {
            throw ServiceException(HttpStatus.UNAUTHORIZED.value(), "username or password is invalid")
        }

        return JwtHelper.createJwt(user.id!!, user.username)
    }

    @Transactional
    open fun signUp(username: String, password: String, nickname: String): String {
        if (userRepository.findByUsername(username).isNotNull())
            throw ServiceException(HttpStatus.BAD_REQUEST.value(), "$username is already exists.")

        val user = User(nickname, username, passwordEncoder.encode(password))
        userRepository.save(user)
        return JwtHelper.createJwt(user.id!!, user.username)
    }
}
