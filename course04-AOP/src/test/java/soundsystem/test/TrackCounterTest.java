package soundsystem.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import soundsystem.TrackCounter;
import soundsystem.album.CompactDisc;
import soundsystem.config.SoundSystemConfig;

/**
 * @author justZero
 * @since 2018/12/31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoundSystemConfig.class)
public class TrackCounterTest {

    @Autowired
    private CompactDisc cd;

    @Autowired
    private TrackCounter counter;

    @Test
    public void testTrackCounter() {
        cd.info();

        cd.playTrack(0);
        cd.playTrack(1);
        cd.playTrack(3);
        cd.playTrack(3);
        cd.playTrack(3);
        cd.playTrack(3);
        cd.playTrack(5);
        cd.playTrack(5);

        Assert.assertEquals(1, counter.getPlayCount(0));
        Assert.assertEquals(1, counter.getPlayCount(1));
        Assert.assertEquals(0, counter.getPlayCount(2));
        Assert.assertEquals(4, counter.getPlayCount(3));
        Assert.assertEquals(0, counter.getPlayCount(4));
        Assert.assertEquals(2, counter.getPlayCount(5));
    }

}
