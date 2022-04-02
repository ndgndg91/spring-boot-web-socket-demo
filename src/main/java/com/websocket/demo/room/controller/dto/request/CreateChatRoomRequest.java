package com.websocket.demo.room.controller.dto.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class CreateChatRoomRequest {
    private String title;
}
