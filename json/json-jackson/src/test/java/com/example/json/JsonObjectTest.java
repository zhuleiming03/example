package com.example.json;

import com.example.json.domain.SubjectPO;
import com.example.json.service.JacksonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonObjectTest {

    @Autowired
    JacksonService service;

    @Test
    public void subjectTest() throws Exception {

        SubjectPO po = new SubjectPO();
        po.setName("八神太一");
        po.setBillItemID(11);
        po.setBillID(1);
        po.setAmount(new BigDecimal(15.56));

        String json = service.toJson(po);

        System.out.println(json);
    }
}
