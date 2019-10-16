package com.example.net.controller;

import com.example.net.dto.BaseRequestDTO;
import com.example.net.dto.BaseResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestTemplateControl {

    @GetMapping("/simpleGet")
    public ResponseEntity<String> simpleGet() {
        return ResponseEntity.ok("ok");
    }

    @GetMapping("/multiplicityGet")
    public ResponseEntity<BaseResponseDTO> multiplicityGet(@RequestParam String id, @RequestParam String name) {
        BaseResponseDTO response = new BaseResponseDTO();
        response.setCode(id);
        response.setStatus("00" + id);
        response.setMessage(name);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/simplePost")
    public BaseResponseDTO simplePost(@RequestBody BaseRequestDTO request) {
        BaseResponseDTO response = new BaseResponseDTO();
        response.setStatus(request.getId().toString());
        response.setCode("00" + request.getId());
        response.setMessage("ID:" + request.getId());
        return response;
    }
}
