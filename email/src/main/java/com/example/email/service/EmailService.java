package com.example.email.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendSimpleEmail() {
        // 建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        // 发送者
        mainMessage.setFrom("zhuleiming@vcredit.com");
        // 接收者
        mainMessage.setTo("zhuleiming@vcredit.com");
        // 发送的标题
        mainMessage.setSubject("EmailTest");
        // 发送的内容
        mainMessage.setText("SpringBoot Email Test!");
        javaMailSender.send(mainMessage);
    }

    public void sendAttachmentsMail() {

        try {

            File file = ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "excel/templet1.xlsx");
            String[] Consignee = {"zhuleiming@vcredit.com", "liujixin@vcredit.com"};

            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("zhuleiming@vcredit.com");
            helper.setTo(Consignee);
            helper.setCc(Consignee);
            helper.setSubject("EmailTest");
            helper.setText("SpringBoot Email Test!");
            helper.addAttachment("templet.xlsx", file);

            javaMailSender.send(message);
            log.info("带附件的邮件发送成功");
        } catch (IOException e) {
            log.error("获取模板有误", e);
        } catch (MessagingException e) {
            log.error("发送带附件的邮件失败", e);
        }
    }

}
