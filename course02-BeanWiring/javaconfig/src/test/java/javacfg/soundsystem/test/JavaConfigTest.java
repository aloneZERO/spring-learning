package javacfg.soundsystem.test;

import javacfg.soundsystem.config.CDPlayerConfig;
import javacfg.soundsystem.player.MediaPlayer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试——注解显示装配Bean
 * @author justZero
 * @since 2018/12/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class JavaConfigTest {

    @Autowired
    private MediaPlayer player;

    @Test
    public void testPlayCD() {
        player.play();
    }

}
