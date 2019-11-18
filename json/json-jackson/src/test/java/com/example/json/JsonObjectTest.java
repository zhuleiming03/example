package com.example.json;

import com.example.json.domain.SubjectPO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
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
        po.setCreateTime(new Timestamp(System.currentTimeMillis()));
        po.setBillDay(LocalDate.now());

        String json = objectMapper.writeValueAsString(po);

        System.out.println(json);
    }

    @Test
    public void stringToObject() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{\"billItemId\":11,\"billId\":1,\"amount\":15.56,\"name\":\"八神太一\"}";

        SubjectPO subjectPO = objectMapper.readValue(json, SubjectPO.class);

        System.out.println(subjectPO);
    }
}
