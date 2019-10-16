package com.example.net.service;

import com.example.net.dto.BaseRequestDTO;
import com.example.net.dto.BaseResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestTemplateService {

    @Value("${testServer.Address}")
    private String serviceAddress;

    private final ObjectMapper mapper;

    public void simpleGet() {
        String url = serviceAddress + "/simpleGet";
        log.info(">> simpleGet input:{}", url);
        ResponseEntity<String> result = new RestTemplate().getForEntity(url, String.class);
        log.info(">> simpleGet ouput:{}", result.getBody());
    }

    public void simplePost() throws JsonProcessingException {
        String url = serviceAddress + "/simplePost";
        BaseRequestDTO request = new BaseRequestDTO();
        request.setId(9);
        log.info(">> simplePost input:{}", mapper.writeValueAsString(request));
        BaseResponseDTO result = new RestTemplate().postForObject(url, request, BaseResponseDTO.class);
        log.info(">> simplePost ouput:{}", mapper.writeValueAsString(result));
    }

    public void multiplicityGet() throws JsonProcessingException {
        String url = serviceAddress + "/multiplicityGet?id=8&name=圣人";
        log.info(">> multiplicityGet input:{}", url);
        BaseResponseDTO result = new RestTemplate().getForObject(url, BaseResponseDTO.class);
        log.info(">> multiplicityGet ouput:{}", mapper.writeValueAsString(result));
    }
}
