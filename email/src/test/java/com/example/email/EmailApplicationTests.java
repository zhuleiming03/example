package com.example.email;

import com.example.email.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    EmailService service;

    @Test
    public void SimpleEmailTest(){
        service.sendSimpleEmail();
    }

    @Test
    public void AttachmentsMailTest(){
        service.sendAttachmentsMail();
    }

}
