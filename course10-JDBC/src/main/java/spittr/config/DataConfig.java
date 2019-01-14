package spittr.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * 激活对应的数据源 profile 的常用方法：
 * 1. 见 {@link spittr.config.WebAppInitializer} 中的 onStartup 方法；
 * 2. 配置 Spring 环境变量，即加载相应配置的属性文件（如 app.properties）
 *
 * @author justZero
 * @since 2019/1/14
 */
@Configuration
@ComponentScan(value = "spittr",
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ANNOTATION,
                        value = EnableWebMvc.class
                )
        })
@PropertySource("classpath:app.properties")
public class DataConfig {

    // 使用 JNDI 数据源
    @Bean
    @Profile("prod") // 生产环境的数据源
    public DataSource prodDataSource() {
        JndiObjectFactoryBean jndiObjectFB = new JndiObjectFactoryBean();
        jndiObjectFB.setJndiName("jdbc/SpittrDS");
        jndiObjectFB.setResourceRef(true);
        jndiObjectFB.setProxyInterface(DataSource.class);
        return (DataSource) jndiObjectFB.getObject();
    }

    // 使用 Apache Commons DBCP2 数据源
    @Bean
    @Profile("qa") // QA 数据源
    public DataSource qaDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spittr");
        ds.setUsername("user");
        ds.setPassword("passwd");
        ds.setInitialSize(5); // 连接池启动时创建5个连接
        ds.setMaxTotal(10); // 允许创建新连接，但最大活跃连接数为10
        return ds;
    }

    // 还有基于 JDBC 驱动的数据源（不推荐使用——性能差）
    // 1. DriveManagerDataSource: 每次连接请求都返回一个新建连接，没有池化管理；
    // 2. SimpleDriverDataSource: 与(1)类似，不过是直接使用 JDBC 驱动；
    // 3. SingleConnectionDataSource: 可看作是只有一个连接的池。


    // 使用嵌入式数据源
    @Bean
    @Profile("dev") // 开发环境的数据源
    public DataSource devDataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("sql/schema.sql")
                .addScript("sql/test-data.sql")
                .build();
    }
}
