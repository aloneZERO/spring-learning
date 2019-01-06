package com.springlearn.soundsystem;


import com.springlearn.soundsystem.album.CompactDisc;

/**
 * @author justZero
 * @since 2018/12/28
 */
public class CDPlayer implements MediaPlayer {

    private CompactDisc cd;

    public CDPlayer(CompactDisc cd) {
        this.cd = cd;
    }

    @Override
    public void play() {
        cd.play();
    }
}
