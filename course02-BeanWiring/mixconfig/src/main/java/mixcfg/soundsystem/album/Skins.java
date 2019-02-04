package mixcfg.soundsystem.album;

/**
 * 专辑《Skins》——Xxxtentacion
 * @author justZero
 * @since 2018/12/28
 */
public class Skins implements CompactDisc {

    private final String title = "Skins";
    private final String artist = "Xxxtentacion";

    @Override
    public void play() {
        System.out.println("正在播放：" + title + "，歌手：" + artist);
    }

}
