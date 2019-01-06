package com.springlearn.soundsystem.album;

/**
 * 专辑《Bad》——Xxxtentacion
 * @author justZero
 * @since 2018/12/28
 */
public class Bad implements CompactDisc {

    private final String title = "Bad";
    private final String artist = "Xxxtentacion";

    @Override
    public void play() {
        System.out.println("正在播放：" + title + "，歌手：" + artist);
    }
}
