package xmlcfg.soundsystem.album;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlankDisc implements CompactDisc {

    private String title;
    private String artist;
    private List<String> tracks;

    public BlankDisc(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public void play() {
        System.out.println("正在播放：" + title + "，歌手：" + artist);
        if (tracks != null) {
            for (String track : tracks) {
                System.out.println("    -磁道：" + track);
            }
        }
    }

}