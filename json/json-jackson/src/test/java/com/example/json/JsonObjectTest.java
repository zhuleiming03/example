package com.example.json;

import com.example.json.domain.SubjectPO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JsonObjectTest {

    @Test
    public void objectToString() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        SubjectPO po = new SubjectPO();
        po.setName("八神太一");
        po.setBillItemId(11L);
        po.setBillId(1);
        po.setAmount(new BigDecimal(15.56));
        po.setCreateTime(LocalDateTime.now());

        String json = objectMapper.writeValueAsString(po);

        System.out.println(json);
    }
}
