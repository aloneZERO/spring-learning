package mail.demo.test;

import com.icegreen.greenmail.util.GreenMail;
import mail.demo.config.GreenMailConfig;
import mail.demo.domain.Spitter;
import mail.demo.domain.Spittle;
import mail.demo.service.MailService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * GreenMail 支持各种邮件协议，本身可作为模拟服务器，并提供虚拟用户。
 *
 * 总是报验证失败的异常...暂未解决。
 *
 * @author justZero
 * @since 2019-1-27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = GreenMailConfig.class)
public class GreenMailTest {

    @Autowired
    private MailService mailService;

    @Autowired
    private GreenMail mailServer;

    @Before
    public void setup() {
        mailServer.start();
    }

    @After
    public void cleanup() {
        mailServer.stop();
    }

    @Test
    public void sendSimpleSpittleEmail() throws Exception {
        Spitter spitter = new Spitter(1L, "测试哥", null,
                "测试哥", "test@spring.com", true);
        Spittle spittle = new Spittle(1L, spitter, "你好啊!", new Date());
        mailService.sendSimpleSpittleEmail("try@gmail.com", spittle);

        MimeMessage[] receivedMessages = mailServer.getReceivedMessages();
        assertEquals(1, receivedMessages.length);
        assertEquals("来自测试哥的新消息", receivedMessages[0].getSubject());
        assertEquals("测试哥说到：你好啊!", ((String) receivedMessages[0].getContent()).trim());
        Address[] from = receivedMessages[0].getFrom();
        assertEquals(1, from.length);
        assertEquals("noreply@spittr.com", ((InternetAddress) from[0]).getAddress());
        assertEquals("try@gmail.com",
                ((InternetAddress) receivedMessages[0]
                        .getRecipients(RecipientType.TO)[0])
                        .getAddress());
    }

}
