package com.example.net.controller;

import com.example.net.dto.BaseRequestDTO;
import com.example.net.dto.BaseResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class RestTemplateControl {

    @GetMapping("/simpleGet")
    public ResponseEntity<String> simpleGet() {
        log.info(">> simpleGet output:ok");
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/multiplicityGet")
    public ResponseEntity<BaseResponseDTO> multiplicityGet(@RequestParam String id, @RequestParam String name) {
        log.info(String.format(">> simpleGet input: id: %s ; name: %s ", id, name));
        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode(id);
        response.setStatus("00" + id);
        response.setMessage(name);
        log.info(">> simpleGet ouput:{}", response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/simplePost")
    public BaseResponseDTO simplePost(@RequestBody BaseRequestDTO request) {
        log.info(">> simpleGet input:{}", request);
        BaseResponseDTO response = new BaseResponseDTO();
        response.setStatus(request.getId().toString());
        response.setCode("00" + request.getId());
        response.setMessage("ID:" + request.getId());
        log.info(">> simpleGet ouput:{}", response);
        return response;
    }
}
