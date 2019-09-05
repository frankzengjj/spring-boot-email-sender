package com.tianyuzeng.springbootemail.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Resource
    MailService mailService;

    @Resource
    TemplateEngine templateEngine;

    @Test
    public void sayHelloTest() {
        mailService.sayHello();
    }

    @Test
    public void sendSimpleMailTest() {
        mailService.sendSimpleMail("fzeng6@gmail.com", "first email", "hello world");
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content = "<html>\n" +
                "<body>\n" +
                    "<h3> hello, this is a html mail</h3>\n" +
                "</body>\n" +
                "</html>";

        mailService.sendHtmlMail("fzeng6@gmail.com", "first email", content);
    }

    @Test
    public void sendAttachmentMail() throws MessagingException {
        String filePath =  "/home/tianyu/Desktop/testAttachment.txt";
        mailService.sendAttachmentMail("fzeng6@gmail.com", "attachment email", "see attachment", filePath);
    }

    @Test
    public void sendInlineResourceMailTest() throws MessagingException {
        String filePath = "";
        String rscId = "001";
        String content = "<html><body>email with picture：<img src=\'cid:" +
                rscId + "\'></img></body></html>";
        mailService.sendInlineResource("fzeng6@gmail.com", "attachment email", "see attachment", filePath, rscId);
    }

    @Test
    public void templateEmailTest() throws MessagingException {
        Context context = new Context();

        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("fzeng6@gmail.com", "first email", emailContent);
    }

}
