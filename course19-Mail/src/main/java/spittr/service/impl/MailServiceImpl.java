package spittr.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;
import spittr.domain.Spittle;
import spittr.service.MailService;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private JavaMailSenderImpl mailSender;

    private ITemplateEngine thymeleaf;

    @Override
    public void sendSimpleSpittleEmail(String to, Spittle spittle)
            throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        String spitterName = spittle.getSpitter().getFullName();

        // 自定义发件人名称
        String nickname = MimeUtility.encodeText("Spring 邮件测试");
        InternetAddress from = new InternetAddress(
                nickname+" <"+mailSender.getUsername()+">");

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("来自" + spitterName + "的新消息");
        helper.setText(spitterName + "说到：" + spittle.getText());
        mailSender.send(message);
    }

    @Override
    public void sendSpittleEmailWithAttachment(String to, Spittle spittle)
            throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String spitterName = spittle.getSpitter().getFullName();

        // 自定义发件人名称
        String nickname = MimeUtility.encodeText("Spring 邮件测试");
        InternetAddress from = new InternetAddress(
                nickname+" <"+mailSender.getUsername()+">");

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("来自" + spitterName + "的新消息");
        helper.setText(spitterName + "说到：" + spittle.getText());
        ClassPathResource image = new ClassPathResource("/attachment/avatar.jpg");
        helper.addAttachment("附件.jpg", image);
        mailSender.send(message);
    }

    @Override
    public void sendSpittleEmailWithRichText(String to, Spittle spittle)
            throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
        String spitterName = spittle.getSpitter().getFullName();

        // 邮件正文使用 Thymeleaf 模板（HTML）
        Context context = new Context();
        context.setVariable("spitterName", spitterName);
        context.setVariable("spittleText", spittle.getText());
        context.setVariable("image", "cid:spitterLogo");
        String emailText = thymeleaf.process("template.html", context);

        // 自定义发件人名称
        String nickname = MimeUtility.encodeText("Spring 邮件测试");
        InternetAddress from = new InternetAddress(
                nickname+" <"+mailSender.getUsername()+">");

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("来自" + spitterName + "的新消息");
        helper.setText(emailText, true);
        ClassPathResource image = new ClassPathResource("/attachment/avatar.jpg");
        helper.addInline("spitterLogo", image); // 图片内嵌到富文本里，而不是附件
        mailSender.send(message);
    }

}
