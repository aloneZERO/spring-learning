package spittr.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import spittr.domain.Spittle;
import spittr.service.SpitterMailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class SpitterMailServiceImpl implements SpitterMailService {

    private JavaMailSender mailSender;

    @Override
    public void sendSimpleSpittleEmail(String to, Spittle spittle) {
        SimpleMailMessage message = new SimpleMailMessage();
        String spitterName = spittle.getSpitter().getFullName();
        message.setFrom("noreply@spitter.com");
        message.setTo(to);
        message.setSubject("New spittle from " + spitterName);
        message.setText(spitterName + " says: " + spittle.getText());
        mailSender.send(message);
    }

    @Override
    public void sendSpittleEmailWithAttachment(String to, Spittle spittle)
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        String spitterName = spittle.getSpitter().getFullName();
        helper.setFrom("noreply@spitter.com");
        helper.setTo(to);
        helper.setSubject("New spittle from " + spitterName);
        helper.setText(spitterName + " says: " + spittle.getText());
        ClassPathResource couponImage = new ClassPathResource("/attachment/avatar.jpg");
        helper.addAttachment("Avatar.jpg", couponImage);
        mailSender.send(message);
    }

}
