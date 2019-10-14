package com.example.verify.controller;

import com.example.verify.dto.RequestDTO;
import com.example.verify.dto.ResponseDTO;
import com.example.verify.service.MappingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MappingService mappingService;

    @PostMapping("/test")
    public ResponseDTO simplePost(@RequestBody RequestDTO request) {

        Map<String, Object> requestMap = mappingService.requestConvert(request);

        ResponseDTO response = new ResponseDTO();
        response.setStatus(1);
        response.setCode(200);
        response.setMessage("ID:" + request.getId());
        return response;
    }
}
