package com.websocket.demo.chatroom.controller.dto.request


data class CreateChatRoomRequest(val title: String) {
    /**
     * jackson deserialize creator
     */
    constructor(): this("")
}