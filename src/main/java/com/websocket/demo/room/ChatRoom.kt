package com.websocket.demo.room;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class ChatRoom {
    private String id;
    private String title;
    private LocalDateTime lastModifiedAt;
    private LocalDateTime createdAt;

    /**
     * for redis deserialize
     */
    public ChatRoom(String id) {
        this.id = id;
    }
}
