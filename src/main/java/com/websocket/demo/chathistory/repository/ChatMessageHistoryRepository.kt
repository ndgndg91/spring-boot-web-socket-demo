package com.websocket.demo.chathistory.repository

import com.websocket.demo.chathistory.ChatMessageHistory
import org.springframework.data.jpa.repository.JpaRepository

interface ChatMessageHistoryRepository: JpaRepository<ChatMessageHistory, Long>