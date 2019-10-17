package com.example.net;

import com.example.net.dto.BaseRequestDTO;
import com.example.net.service.RestTemplateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiCallTest {

    //运行此Test前，请先package项目，并运行jar
    //java -jar net.jar --spring.profiles.active=prod

    @Autowired
    RestTemplateService service;

    @Test
    public void simpleGetTest() {
        String result = service.simpleGet();
        System.out.println("result:" + result);
    }

    @Test
    public void simplePostTest() throws JsonProcessingException {
        BaseRequestDTO request = new BaseRequestDTO();
        request.setId(9);
        String result = service.simplePost(request);
        System.out.println("result:" + result);
    }

    @Test
    public void multiplicityGetTest() throws JsonProcessingException {
        Map<String, String> map = new HashMap<>();
        map.put("id", "5");
        map.put("name", "圣人惠");
        String result = service.multiplicityGet(map);
        System.out.println("result:" + result);
    }
}
