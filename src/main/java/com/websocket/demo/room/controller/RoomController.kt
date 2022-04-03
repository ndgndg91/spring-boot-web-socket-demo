package com.websocket.demo.room.controller

import com.websocket.demo.room.ChatRoom
import com.websocket.demo.room.controller.dto.request.CreateChatRoomRequest
import com.websocket.demo.room.service.RoomService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
open class RoomController(private val roomService: RoomService) {

    @GetMapping("/apis/rooms")
    fun findRooms(): ResponseEntity<List<ChatRoom>> {
        return ResponseEntity.ok(roomService.findAll())
    }

    @GetMapping("/apis/rooms/{id}")
    fun findById(@PathVariable id: String?): ResponseEntity<ChatRoom> {
        return ResponseEntity.ok(roomService.findById(id))
    }

    @PostMapping("/apis/rooms")
    fun createRoom(@RequestBody request: CreateChatRoomRequest): ResponseEntity<Void> {
        val id = roomService.create(request.title)
        return ResponseEntity.created(URI.create("/apis/rooms/$id")).build()
    }
}