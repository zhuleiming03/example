package example.spring.boot.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
class JsonArrayTest {

    @Resource
    private ObjectMapper objectMapper;

    @Test
    void listToString() throws JsonProcessingException {

        List<Integer> lists = new LinkedList<>();
        lists.add(321);
        lists.add(123);
        lists.add(63);
        lists.add(9632);

        String json = objectMapper.writeValueAsString(lists);
        System.out.println(json);
    }

    @Test
    void stringToList() throws JsonProcessingException {

        String json = "[321,123,63,9632]";

        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, Integer.class);
        List<Integer> list = objectMapper.readValue(json, javaType);
        System.out.println(list);
    }

    @Test
    void mapToString() throws JsonProcessingException {

        Map<String, Object> map = new HashMap<>();
        map.put("age", 25);
        map.put("name", "小樱");
        map.put("interests", new String[]{"PC games", "Music"});

        String json = objectMapper.writeValueAsString(map);
        System.out.println(json);
    }

    @Test
    void stringToMap() throws JsonProcessingException {

        String json = "{\"name\":\"小樱\",\"interests\":[\"PC games\",\"Music\"],\"age\":25}";

        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map);
    }
}
