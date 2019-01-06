package com.springlearn.demo.test;

import com.springlearn.demo.disc.BlankDisc;
import com.springlearn.demo.disc.Disc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 测试 Spring 表达式
 *
 * @author justZero
 * @since 2018/12/30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spel-config.xml")
public class SpEL_Test {

    @Value("#{T(System).currentTimeMillis()}")
    private long currentTime;

    @Value("#{appProps['disc.title']}")
    private String title;

    @Value("#{appProps['disc.artist']}")
    private String artist;

    @Value("#{3.14159}")
    private float pi;

    @Value("#{6.66E2}")
    private int number;

    @Value("#{'Hello'}")
    private String hello;

    @Value("#{false}")
    private boolean fake;

    @Value("#{T(java.lang.Math).random()}")
    private double random;

    @Value("#{T(java.lang.Math).PI * circle.radius ^ 2}")
    private double circleArea;

    // 正则匹配
    @Value("#{'abc@yeahcom' matches '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\\.com'}")
    private boolean invalidEmail;

    @Value("#{'abc@qq.com' matches '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\\.com'}")
    private boolean validEmail;

    // 集合操作
    @Value("#{'This is a test'[3]}")
    private char ch;

    // 取歌手名为'三无'的所有歌曲
    @Value("#{musicbox.songs.?[artist == '三无']}")
    private List<BlankDisc> likedSongs;

    // 取歌手名为'三无'的第一首歌曲
    @Value("#{musicbox.songs.^[artist == '三无']}")
    private List<BlankDisc> firstLikedSong;

    // 取歌手名为'三无'的最后一首歌曲
    @Value("#{musicbox.songs.$[artist == '三无']}")
    private List<BlankDisc> lastLikedSong;

    // 取所有的歌曲标题集合
    @Value("#{musicbox.songs.![title]}")
    private List<String> allTitles;

    @Test
    public void test() {
        System.out.println(currentTime);
        new BlankDisc(title, artist).info();
        System.out.println(random);
        System.out.println(circleArea);

        assertTrue( (pi==3.14159f) );
        assertTrue( (number==666) );
        assertEquals("Hello", hello);
        assertFalse(fake);
        assertFalse(invalidEmail);
        assertTrue(validEmail);
        assertEquals('s', ch);
    }

    @Test
    public void testLikedSong1() {
        for (Disc song: likedSongs) {
            song.info();
        }
    }

    @Test
    public void testLikedSong2() {
        for (Disc song: firstLikedSong) {
            song.info();
        }
    }

    @Test
    public void testLikedSong3() {
        for (Disc song: lastLikedSong) {
            song.info();
        }
    }

    @Test
    public void testAllTitlesOfSong() {
        assertEquals(3, allTitles.size());
        System.out.println(allTitles);
    }

}
