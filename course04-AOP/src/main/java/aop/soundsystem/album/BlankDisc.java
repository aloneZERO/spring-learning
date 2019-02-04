package aop.soundsystem.album;

import lombok.Data;

import java.util.List;

/**
 * @author justZero
 * @since 2018/12/31
 */
@Data
public class BlankDisc implements CompactDisc {

    private String title;
    private String artist;
    private List<String> tracks;

    @Override
    public void info() {
        System.out.println("专辑："+title+"，歌手："+artist);
    }

    @Override
    public void playTrack(int trackNumber) {
        try {
            System.out.println("正在播放："+tracks.get(trackNumber));
        } catch (Exception e) {
            System.out.println("没有这首歌");
        }
    }
}
