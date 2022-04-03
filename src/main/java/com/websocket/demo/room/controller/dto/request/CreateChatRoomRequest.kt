package com.websocket.demo.room.controller.dto.request


data class CreateChatRoomRequest(val title: String) {
    /**
     * jackson deserialize creator
     */
    constructor(): this("")
}