package com.springlearn.soundsystem.test;

import com.springlearn.soundsystem.config.CDPlayerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.springlearn.soundsystem.MediaPlayer;

/**
 * 测试XML和注解一起使用
 *
 * @author justZero
 * @since 2018/12/29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class MixConfigTest {

    @Autowired
    private MediaPlayer player;

    @Test
    public void testPlayer() {
        player.play();
    }

}
