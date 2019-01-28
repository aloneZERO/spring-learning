package restclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author justZero
 * @since 2019/1/28
 */
@Configuration
@ComponentScan("restclient")
public class RootConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
