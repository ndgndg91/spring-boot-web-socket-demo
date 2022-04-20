package com.websocket.demo.chatroom.repository

import com.websocket.demo.chatroom.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRoomRepository: JpaRepository<ChatRoom, Long> {
}