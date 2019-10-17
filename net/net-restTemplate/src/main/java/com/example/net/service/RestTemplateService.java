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

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestTemplateService {

    @Value("${TestAddress}")
    private String serviceAddress;

    private final ObjectMapper mapper;

    private final String SIMPLEGET="/simpleGet";

    private final String SIMPLEPOST="/simplePost";

    private final String MULTIPLICITYGET="/multiplicityGet";

    public String simpleGet() {
        String url = serviceAddress + SIMPLEGET;
        log.info(">> simpleGet input:{}", url);
        ResponseEntity<String> result = new RestTemplate().getForEntity(url, String.class);
        log.info(">> simpleGet ouput:{}", result.getBody());
        return result.getBody();
    }

    public String simplePost(BaseRequestDTO request) throws JsonProcessingException {
        String url = serviceAddress + SIMPLEPOST;
        log.info(">> simplePost input:{}", mapper.writeValueAsString(request));
        BaseResponseDTO result = new RestTemplate().postForObject(url, request, BaseResponseDTO.class);
        log.info(">> simplePost ouput:{}", mapper.writeValueAsString(result));
        return mapper.writeValueAsString(result);
    }

    public String multiplicityGet(Map<String,String> params) throws JsonProcessingException {
        String id = params.get("id");
        String name = params.get("name");
        String url = String.format("%s%s?id=%s&name=%s", serviceAddress, MULTIPLICITYGET, id, name);
        log.info(">> multiplicityGet input:{}", url);
        BaseResponseDTO result = new RestTemplate().getForObject(url, BaseResponseDTO.class);
        log.info(">> multiplicityGet ouput:{}", mapper.writeValueAsString(result));
        return mapper.writeValueAsString(result);
    }
}
