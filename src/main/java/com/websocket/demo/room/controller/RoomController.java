package com.websocket.demo.room.controller;

import com.websocket.demo.room.ChatRoom;
import com.websocket.demo.room.controller.dto.request.CreateChatRoomRequest;
import com.websocket.demo.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/apis/rooms")
    public ResponseEntity<List<ChatRoom>> findRooms() {
        return ResponseEntity.ok(roomService.findAll());
    }

    @GetMapping("/apis/rooms/{id}")
    public ResponseEntity<ChatRoom> findById(@PathVariable String id) {
        return ResponseEntity.ok(roomService.findById(id));
    }

    @PostMapping("/apis/rooms")
    public ResponseEntity<Void> createRoom(@RequestBody CreateChatRoomRequest request) {
        var id = roomService.create(request.getTitle());
        return ResponseEntity.created(URI.create("/apis/rooms/" + id)).build();
    }
}
