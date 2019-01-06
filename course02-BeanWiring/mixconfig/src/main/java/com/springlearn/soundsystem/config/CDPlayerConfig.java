package com.springlearn.soundsystem.config;

import com.springlearn.soundsystem.CDPlayer;
import com.springlearn.soundsystem.album.CompactDisc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 使用@Import和@ImportResource导入其它配置
 *
 * @author justZero
 * @since 2018-12-29
 */
@Configuration
@ImportResource("cd-config.xml")
public class CDPlayerConfig {

    @Bean
    public CDPlayer cdPlayer(CompactDisc cd) {
        return new CDPlayer(cd);
    }

}
