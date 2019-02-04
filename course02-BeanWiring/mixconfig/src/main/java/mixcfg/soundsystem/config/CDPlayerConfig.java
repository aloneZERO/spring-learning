package mixcfg.soundsystem.config;

import mixcfg.soundsystem.player.CDPlayer;
import mixcfg.soundsystem.album.CompactDisc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * 使用 @Import 导入其它 java 文件配置；
 * 使用 @ImportResource 导入其它 XML 配置。
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
