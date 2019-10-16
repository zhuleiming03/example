package com.example.net;

import com.example.net.dto.BaseRequestDTO;
import com.example.net.dto.BaseResponseDTO;
import com.example.net.service.HttpClientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NetApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println("测试前，请先启动 net-restTemplate 项目");
    }

    @Autowired
    HttpClientService service;

    @Test
    public void callServletTest() {
        BaseRequestDTO request = new BaseRequestDTO();
        request.setId(5);
        BaseResponseDTO response = service.simplePost(request);
        System.out.println("result:" + response);
    }

}
