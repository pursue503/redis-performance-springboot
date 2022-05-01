package com.test.redis.controller;

import com.test.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RedisController {

    private final RedisService redisService;

    @PostMapping("/redis/set")
    public String setRedis() {
        redisService.setUp();

        return "SUCCESS";
    }

    @GetMapping("{id}")
    public String get(@PathVariable("id") String id) {
        return redisService.get(id);
    }

    @GetMapping("/all")
    public Set<String> getAll() {
        return redisService.keys();
    }


}
