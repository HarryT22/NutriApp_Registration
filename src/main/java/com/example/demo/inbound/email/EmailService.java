package com.example.demo.inbound.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.IllegalWriteException;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@AllArgsConstructor
public class EmailService implements EmailSender {
    private  final  static Logger LOGGER=LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    private  final SimpleMailMessage preConfiguredMessage;
    @Override
    @Async
    public void send(String to, String email) throws MailException {
        try{
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setFrom("NUTriton@web.de");
            message.setSubject("Bestätigung Nutrition App");

            message.setText("");
            mailSender.send(message);
        }catch (MailException e){
            LOGGER.error("failed",e);
            throw new IllegalStateException("faikled");
        }


    }
}
