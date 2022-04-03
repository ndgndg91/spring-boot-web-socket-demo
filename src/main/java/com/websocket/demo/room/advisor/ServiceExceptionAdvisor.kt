package com.websocket.demo.room.advisor

import com.websocket.demo.room.ApiError
import com.websocket.demo.room.exception.ServiceException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ServiceExceptionAdvisor {

    @ExceptionHandler(ServiceException::class)
    fun serviceException(e: ServiceException): ResponseEntity<ApiError> {
        return ResponseEntity.status(e.status).body(ApiError(e.message))
    }
}