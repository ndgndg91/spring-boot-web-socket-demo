package com.websocket.demo.chathistory.service

import com.websocket.demo.chathistory.ChatMessageHistory
import com.websocket.demo.chathistory.repository.ChatMessageHistoryRepository
import com.websocket.demo.handler.ChatEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ChatMessageHistoryService(private val chatMessageHistoryRepository: ChatMessageHistoryRepository) {

    @Async
    @Transactional
    @EventListener
    fun writeHistory(event: ChatEvent) {
        val history = ChatMessageHistory(event.userId, event.roomId, event.writer, event.contents)
        chatMessageHistoryRepository.save(history)
    }
}