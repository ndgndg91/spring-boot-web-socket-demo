package com.websocket.demo.room.repository;

import com.websocket.demo.room.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Repository
@RequiredArgsConstructor
public class RoomRepository {

    private static final String ROOM_KEY = "rooms";
    private final RedisTemplate<String, ChatRoom> redisTemplate;


    @PostConstruct
    void rooms() {
        redisTemplate.delete(ROOM_KEY);
        var chatRooms = List.of(
                new ChatRoom(UUID.randomUUID().toString(), "20대 소통방", null, LocalDateTime.now()),
                new ChatRoom(UUID.randomUUID().toString(), "모바일 던파", null, LocalDateTime.now()),
                new ChatRoom(UUID.randomUUID().toString(), "카트라이더 러쉬 플러스", null, LocalDateTime.now()),
                new ChatRoom(UUID.randomUUID().toString(), "주식 공부방", null, LocalDateTime.now()),
                new ChatRoom(UUID.randomUUID().toString(), "30대 직장인방", null, LocalDateTime.now()),
                new ChatRoom(UUID.randomUUID().toString(), "무엇이든 물어보살", null, LocalDateTime.now())
        );

        for (var r : chatRooms) {
            log.info("{}", r);
            redisTemplate.opsForHash().put(ROOM_KEY, r.getId(), r);
        }
    }

    public List<ChatRoom> findAll() {
        return redisTemplate.opsForHash().entries(ROOM_KEY).values().stream()
                .map(v -> (ChatRoom) v)
                .sorted(Comparator.comparing(ChatRoom::getCreatedAt))
                .collect(Collectors.toList());
    }

    public ChatRoom findById(String id) {
        return (ChatRoom) redisTemplate.opsForHash().get(ROOM_KEY, id);
    }

    public String create(String title) {
        var id = UUID.randomUUID().toString();
        boolean success = redisTemplate.opsForHash().putIfAbsent(ROOM_KEY, id, new ChatRoom(id, title, null, LocalDateTime.now()));
        while (!success) {
            id = UUID.randomUUID().toString();
            success = redisTemplate.opsForHash().putIfAbsent(ROOM_KEY, id, new ChatRoom(id, title, null, LocalDateTime.now()));
        }

        return id;
    }
}
