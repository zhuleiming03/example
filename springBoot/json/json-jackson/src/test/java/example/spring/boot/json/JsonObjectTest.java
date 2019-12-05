package example.spring.boot.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.spring.boot.json.po.SubjectPO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
class JsonObjectTest {

    @Resource
    private ObjectMapper objectMapper;


    @Test
    void objectToString() throws JsonProcessingException {

        SubjectPO po = new SubjectPO();
        po.setBillItemId(15981564321L);
        po.setBillId(19);
        po.setDueAmount(new BigDecimal(15.565));
        po.setReceiveAmount(new BigDecimal(9.214));
        po.setName("本金");
        po.setDescribe("月本金");
        po.setBillDay(LocalDate.of(2019, 6, 12));
        po.setDeductTime(LocalTime.of(15, 30));
        po.setCreateTime(new Timestamp(System.currentTimeMillis()));
        po.setUpdateTime(LocalDateTime.now());
        po.setVaild(true);

        String json = objectMapper.writeValueAsString(po);
        System.out.println(json);
    }

    @Test
    void stringToObject() throws JsonProcessingException {

        String json = "{\"billId\":26,\"ID\":15981564321,\"dueAmount\":15.569,\"receiveAmount\":9.2140000000000004121147867408581078052520751953125,\"name\":\"本金\",\"billDay\":\"2019-06-12\",\"deductTime\":\"15:30\",\"createTime\":\"2019-10-05 11:25:51\",\"updateTime\":\"2019-12-05 10:25:30\",\"vaild\":true}";

        SubjectPO subjectPO = objectMapper.readValue(json, SubjectPO.class);
        System.out.println(subjectPO);
    }
}
