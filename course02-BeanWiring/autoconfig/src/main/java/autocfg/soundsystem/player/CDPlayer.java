package autocfg.soundsystem.player;

import autocfg.soundsystem.album.CompactDisc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author justZero
 * @since 2018/12/28
 */
@Component
public class CDPlayer implements MediaPlayer {

    private CompactDisc cd;

    /**
     * 利用 @Qualifier 解决自动装配冲突问题
     * @param cd CD光盘
     */
    @Autowired
    public CDPlayer(@Qualifier("skins") CompactDisc cd) {
        this.cd = cd;
    }

    @Override
    public void play() {
        cd.play();
    }
}
