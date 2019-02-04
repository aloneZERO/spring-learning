package autocfg.soundsystem.test;

import autocfg.soundsystem.config.CDPlayerConfig;
import autocfg.soundsystem.album.CompactDisc;
import autocfg.soundsystem.player.MediaPlayer;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.StandardOutputStreamLog;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * 测试自动装配Bean
 * @author justZero
 * @since 2018/12/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CDPlayerConfig.class)
public class AutoConfigTest {

    @Rule
    public final StandardOutputStreamLog log =
            new StandardOutputStreamLog();

    @Autowired
    private MediaPlayer player;

    /**
     * 解决自动装配冲突问题：
     * 自动装配根据类型装配。如自动装配 CompactDisc ，
     * 但有多个实现了该接口的类被标记为了 spring 组件（@Component），
     * spring 无法判定让谁来，则导致冲突问题。
     *
     * 方法1：@Primary 打上该注解的 bean 会成为首选，仅能一个 bean 使用；
     * 方法2：@Qualifier 打上该注解的 bean 表示取得了装配资格，
     *       和 @Autowired 一起使用，通过自定义的命名指定 bean，
     *       可多个 bean 使用。
     * 注意：@Primary 优先级高于 @Qualifier
     */
    @Autowired
    @Qualifier("sad")
    private CompactDisc cd;

    @Test
    public void cdShouldNotBeNull() {
        assertNotNull(cd);
    }

    @Test
    public void testPlayCD() {
        player.play();
        assertEquals("正在播放：Skins，" +
                "歌手：Xxxtentacion" + System.lineSeparator(),
                log.getLog());
    }

}
