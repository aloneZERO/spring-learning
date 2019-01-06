package com.springlearn.demo.config;

import com.springlearn.demo.knight.BraveKnight;
import com.springlearn.demo.knight.Knight;
import com.springlearn.demo.quest.Quest;
import com.springlearn.demo.quest.SlayDragonQuest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
