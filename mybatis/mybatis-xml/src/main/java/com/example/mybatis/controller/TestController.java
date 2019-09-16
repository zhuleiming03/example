package com.example.mybatis.controller;

import com.example.mybatis.dao.UserDao;
import com.example.mybatis.po.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TestController {

    @GetMapping("/simpleGet")
    public ResponseEntity<String> simpleGet() {
        UserPO po=dao.getUser(10001L);
        return ResponseEntity.ok("ok");
    }

    private final UserDao dao;
}
