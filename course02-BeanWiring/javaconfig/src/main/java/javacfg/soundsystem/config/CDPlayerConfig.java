package javacfg.soundsystem.config;

import javacfg.soundsystem.player.CDPlayer;
import javacfg.soundsystem.album.Bad;
import javacfg.soundsystem.album.CompactDisc;
import javacfg.soundsystem.album.Sad;
import javacfg.soundsystem.album.Skins;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 注解形式——手动显示配置Bean装配
 * @author justZero
 * @since 2018/12/28
 */
@Configuration
public class CDPlayerConfig {

    @Bean(name = "sad")
    public CompactDisc sad() {
        return new Sad();
    }

    @Bean
    @Primary
    public CompactDisc randomXxxtentacionCD() {
        int choice = (int) Math.floor(Math.random()*3);
        switch (choice) {
            case 0: return new Sad();
            case 1: return new Bad();
            case 2: return new Skins();
            default: return new Sad();
        }
    }

    @Bean
    public CDPlayer cdPlayer() {
        return new CDPlayer(randomXxxtentacionCD());
    }
}
