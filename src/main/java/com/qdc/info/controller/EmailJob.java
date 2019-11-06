package com.qdc.info.controller;

import com.qdc.info.bean.Email;
import org.quartz.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;


public class EmailJob implements Job {

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getMergedJobDataMap();
        Email email =(Email) dataMap.get("email");
        JavaMailSenderImpl javaMailSender =(JavaMailSenderImpl) dataMap.get("javaMailSenderImpl");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);
        Scheduler schede =(Scheduler) dataMap.get("scheduler");
        try {
           //邮件对象
           MimeMessage mimeMessage = bean.createMimeMessage();
           MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
           helper.setFrom("17805594005@163.com");
           helper.setTo(email.getEname());
           helper.setSubject(email.getTitle());
           helper.setText(email.getContent(),true);
           //发送邮件
           javaMailSender.send(mimeMessage);
           System.out.println("EMAIL PASS");
           schede.shutdown(true);
       }catch (Exception ex){
            System.out.println(ex.getMessage());
       }
    }
}
