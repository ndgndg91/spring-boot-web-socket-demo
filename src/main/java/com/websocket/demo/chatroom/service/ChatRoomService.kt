package com.websocket.demo.chatroom.service

import com.websocket.demo.chatroom.ChatRoom
import com.websocket.demo.chatroom.exception.ServiceException
import com.websocket.demo.chatroom.repository.ChatRoomRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class ChatRoomService(
    private val chatRoomRepository: ChatRoomRepository
) {

    @PostConstruct
    fun setUp() {
        if (chatRoomRepository.findById(1).isPresent) {
            return
        }

        val chatRooms = listOf(
            ChatRoom("20대 소통방"),
            ChatRoom("모바일 던파", ),
            ChatRoom("카트라이더 러쉬 플러스"),
            ChatRoom("주식 공부방", ),
            ChatRoom("30대 직장인방", ),
            ChatRoom("무엇이든 물어보살", )
        )

        chatRoomRepository.saveAll(chatRooms)
    }

    // TODO : pagination 적용하기
    fun findAll(): List<ChatRoom> {
        return chatRoomRepository.findAll().toList()
    }

    fun findById(id: Long): ChatRoom {
        return chatRoomRepository.findById(id)
            .orElseThrow {
                ServiceException(
                    HttpStatus.NOT_FOUND.value(),
                    String.format("can not find chat room by %s", id)
                )
            }
    }

    fun create(title: String): Long {
        val chatRoom = ChatRoom(title)
        return chatRoomRepository.save(chatRoom).id!!
    }
}