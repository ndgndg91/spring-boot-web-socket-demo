package com.websocket.demo.chatroom.exception

class ServiceException(val status: Int, override val message: String):RuntimeException(message)