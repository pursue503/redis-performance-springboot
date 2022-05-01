package com.test.redis.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    @Async
    public void setUp() {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        for (int i=0; i<1000000; i++) {
            String keyAndValue = String.valueOf(i);

            valueOperations.set(keyAndValue, keyAndValue);
        }
        log.info("DUMMY DATA SET UP SUCCESS");
    }

    public String get(String id) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();

        log.info("Redis Get ==> {}", id);
        return valueOperations.get(id);
    }

    public Set<String> keys() {
        ValueOperations<String, String > valueOperations = redisTemplate.opsForValue();
        Set<String> strings = redisTemplate.keys("*");
        log.info("redis keys count ==> {}", strings.size());
        return strings;
    }

}
