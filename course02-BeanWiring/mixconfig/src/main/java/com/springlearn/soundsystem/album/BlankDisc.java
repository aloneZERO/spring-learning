package com.springlearn.soundsystem.album;

import lombok.*;

/**
 * @author justZero
 * @since 2018/12/29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlankDisc implements CompactDisc {

    private String title;
    private String artist;

    @Override
    public void play() {
        System.out.println("正在播放：" + title + "，歌手：" + artist);
    }
}
