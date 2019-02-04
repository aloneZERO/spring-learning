package testdi.config;

import testdi.knight.BraveKnight;
import testdi.knight.Knight;
import testdi.quest.Quest;
import testdi.quest.SlayDragonQuest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 利用 Java 代码配置，
 * 等同于 knight.xml
 */
@Configuration
public class KnightConfig {

    @Bean
    public Knight knight() {
        return new BraveKnight(quest());
    }

    @Bean
    public Quest quest() {
        return new SlayDragonQuest(System.out);
    }

}
