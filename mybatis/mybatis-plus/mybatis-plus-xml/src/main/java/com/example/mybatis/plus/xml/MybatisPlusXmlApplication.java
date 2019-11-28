package com.example.mybatis.plus.xml;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.mybatis.plus.xml.mapper")
@SpringBootApplication
public class MybatisPlusXmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusXmlApplication.class, args);
    }

}
