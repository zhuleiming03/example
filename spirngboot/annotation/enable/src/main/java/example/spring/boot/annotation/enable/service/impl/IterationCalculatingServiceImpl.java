package example.spring.boot.annotation.enable.service.impl;

import example.spring.boot.annotation.enable.service.CalculatingService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Profile("Java7")
public class IterationCalculatingServiceImpl implements CalculatingService {

    @Override
    public Integer sum(Integer... values) {
        int sum = 0;
        for (Integer value : values) {
            sum += value;
        }
        System.out.printf("[Java 7 Lambda 实现] %s 累加结果 : %d \n",
                Arrays.asList(values), sum);
        return sum;
    }
}
