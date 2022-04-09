package com.websocket.demo.user.controller

import com.websocket.demo.user.controller.dto.request.SignInRequest
import com.websocket.demo.user.controller.dto.response.SignInResponse
import com.websocket.demo.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/apis/users/sign-in")
    fun signIn(@RequestBody request: SignInRequest): ResponseEntity<SignInResponse> {
        val accessToken = userService.signIn(request.username, request.password)
        return ResponseEntity.ok(SignInResponse(request.username, accessToken))
    }
}