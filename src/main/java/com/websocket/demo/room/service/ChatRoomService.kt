package com.websocket.demo.room.service

import com.websocket.demo.room.ChatRoom
import com.websocket.demo.room.exception.ServiceException
import com.websocket.demo.room.repository.ChatRoomRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.annotation.PostConstruct

@Service
open class ChatRoomService(
    private val chatRoomRepository: ChatRoomRepository
) {

    @PostConstruct
    fun setUp() {
        chatRoomRepository.deleteAll()
        val chatRooms = listOf(
            ChatRoom(UUID.randomUUID().toString(), "20대 소통방", null, LocalDateTime.now()),
            ChatRoom(UUID.randomUUID().toString(), "모바일 던파", null, LocalDateTime.now()),
            ChatRoom(UUID.randomUUID().toString(), "카트라이더 러쉬 플러스", null, LocalDateTime.now()),
            ChatRoom(UUID.randomUUID().toString(), "주식 공부방", null, LocalDateTime.now()),
            ChatRoom(UUID.randomUUID().toString(), "30대 직장인방", null, LocalDateTime.now()),
            ChatRoom(UUID.randomUUID().toString(), "무엇이든 물어보살", null, LocalDateTime.now())
        )

        chatRoomRepository.saveAll(chatRooms)
    }

    // TODO : chat room sort 기준 만들고 적용하기
    fun findAll(): List<ChatRoom> {
        return chatRoomRepository.findAll().toList()
    }

    fun findById(id: String): ChatRoom {
        return chatRoomRepository.findById(id)
            .orElseThrow {
                ServiceException(
                    HttpStatus.NOT_FOUND.value(),
                    String.format("can not find chat room by %s", id)
                )
            }
    }

    fun create(title: String): String {
        val chatRoom = ChatRoom(UUID.randomUUID().toString(), title, null, LocalDateTime.now())
        return chatRoomRepository.save(chatRoom).id
    }
}