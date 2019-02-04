package autocfg.soundsystem.album;

import org.springframework.stereotype.Component;

/**
 * 专辑《Skins》——Xxxtentacion
 * @author justZero
 * @since 2018/12/28
 */
@Component
public class Skins implements CompactDisc {

    private final String title = "Skins";
    private final String artist = "Xxxtentacion";

    @Override
    public void play() {
        System.out.println("正在播放：" + title + "，歌手：" + artist);
    }

}
