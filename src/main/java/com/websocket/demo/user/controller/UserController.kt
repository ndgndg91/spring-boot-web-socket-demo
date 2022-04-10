package com.websocket.demo.user.controller

import com.websocket.demo.user.controller.dto.request.SignInRequest
import com.websocket.demo.user.controller.dto.request.SignUpRequest
import com.websocket.demo.user.controller.dto.response.SignInResponse
import com.websocket.demo.user.controller.dto.response.SignUpResponse
import com.websocket.demo.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    /**
     * 회원가입
     */
    @PostMapping("/apis/users")
    fun signUp(@RequestBody request: SignUpRequest): ResponseEntity<SignUpResponse> {
        val accessToken = userService.signUp(request.username, request.password, request.nickname)
        return ResponseEntity.ok(SignUpResponse(request.username, accessToken))
    }

    /**
     * 로그인
     */
    @PostMapping("/apis/users/sign-in")
    fun signIn(@RequestBody request: SignInRequest): ResponseEntity<SignInResponse> {
        val accessToken = userService.signIn(request.username, request.password)
        return ResponseEntity.ok(SignInResponse(request.username, accessToken))
    }
}