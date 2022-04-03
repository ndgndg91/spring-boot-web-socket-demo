package com.websocket.demo.room.service

import com.websocket.demo.room.ChatRoom
import com.websocket.demo.room.repository.RoomRepository
import org.springframework.stereotype.Service

@Service
open class RoomService(private val roomRepository: RoomRepository) {

    fun findAll(): List<ChatRoom> {
        return roomRepository.findAll()
    }

    fun findById(id: String?): ChatRoom {
        return roomRepository.findById(id!!)
    }

    fun create(title: String?): String {
        return roomRepository.create(title!!)
    }
}