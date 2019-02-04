package javacfg.soundsystem.album;

/**
 * 专辑《Sad!》——Xxxtentacion
 * @author justZero
 * @since 2018/12/28
 */
public class Sad implements CompactDisc {

    private final String title = "Sad!";
    private final String artist = "Xxxtentacion";

    @Override
    public void play() {
        System.out.println("正在播放：" + title + "，歌手：" + artist);
    }

}
