package com.websocket.demo.room.repository

import com.websocket.demo.room.ChatRoom
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRoomRepository: CrudRepository<ChatRoom, String> {
}