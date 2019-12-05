package example.spring.boot.json.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * 重写BigDecimal序列化规则，保留2位四舍五入
 */
public class BigDecimalSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator generator, SerializerProvider serializers) throws IOException {
        if (Objects.isNull(value)) {
            generator.writeNull();
        } else {
            // 这里取floor
            generator.writeNumber(value.setScale(2, RoundingMode.FLOOR));
        }
    }
}
