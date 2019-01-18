package orders.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author justZero
 * @since 2019/1/18
 */
@Configuration
@EnableMongoRepositories("orders.dao")
@PropertySource("classpath:mongo.properties")
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongo.url}")
    private String url;
    @Value("${mongo.port}")
    private Integer port;
    @Value("${mongo.username}")
    private String username;
    @Value("${mongo.password}")
    private String password;
    @Value("${mongo.database}")
    private String database;

    @Override
    public MongoClient mongoClient() {
        MongoCredential credential = MongoCredential
                .createCredential(username, database, password.toCharArray());
        MongoClientOptions options = MongoClientOptions.builder().build();
        return new MongoClient(
                new ServerAddress(url, port),
                credential,
                options);
    }

    @Override
    protected String getDatabaseName() {
        return database;
    }
}
