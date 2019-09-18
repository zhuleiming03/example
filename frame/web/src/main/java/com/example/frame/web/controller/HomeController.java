package com.example.frame.web.controller;

import com.example.frame.dao.mapper.UserMapper;
import com.example.frame.domain.po.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final UserMapper userMapper;

    @GetMapping("/index")
    public ResponseEntity<String> simpleGet() {
        UserPO po = userMapper.selectById(10001L);
        return ResponseEntity.ok(po.toString());
    }
}
