package example.spring.boot.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import example.spring.boot.json.domain.LocalDateTimeSerializer;
import example.spring.boot.json.po.SubjectPO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootTest
class JsonObjectTest {

    @Test
    void objectToString() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        JavaTimeModule timeModule = new JavaTimeModule();
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        objectMapper.registerModule(timeModule);

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
    void stringToObject() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        String json = "{\"billItemId\":11,\"billId\":1,\"amount\":15.56,\"name\":\"八神太一\"}";

        SubjectPO subjectPO = objectMapper.readValue(json, SubjectPO.class);

        System.out.println(subjectPO);
    }
}
