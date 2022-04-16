package com.websocket.demo.room.controller

import com.websocket.demo.room.ChatRoom
import com.websocket.demo.room.controller.dto.request.CreateChatRoomRequest
import com.websocket.demo.room.service.ChatRoomService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
class ChatRoomController(private val roomService: ChatRoomService) {

    @GetMapping("/apis/chat-rooms")
    fun findRooms(): ResponseEntity<List<ChatRoom>> {
        return ResponseEntity.ok(roomService.findAll())
    }

    @GetMapping("/apis/chat-rooms/{id}")
    fun findById(@PathVariable id: String): ResponseEntity<ChatRoom> {
        return ResponseEntity.ok(roomService.findById(id))
    }

    @PostMapping("/apis/chat-rooms")
    fun createRoom(@RequestBody request: CreateChatRoomRequest): ResponseEntity<Void> {
        val id = roomService.create(request.title)
        return ResponseEntity.created(URI.create("/apis/rooms/$id")).build()
    }
}