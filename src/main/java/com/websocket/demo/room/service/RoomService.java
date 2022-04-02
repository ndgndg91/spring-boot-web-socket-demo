package com.websocket.demo.room.service;

import com.websocket.demo.room.ChatRoom;
import com.websocket.demo.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;

    public List<ChatRoom> findAll() {
        return roomRepository.findAll();
    }

    public ChatRoom findById(String id) {
        return roomRepository.findById(id);
    }

    public String create(String title) {
        return roomRepository.create(title);
    }
}
