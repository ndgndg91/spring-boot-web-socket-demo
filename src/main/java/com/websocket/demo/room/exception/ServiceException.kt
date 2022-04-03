package com.websocket.demo.room.exception

class ServiceException(val status: Int, override val message: String):RuntimeException(message)