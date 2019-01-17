package spittr.config;

import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author justZero
 * @since 2019/1/16
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("spittr")
@MapperScan("spittr.dao")
public class DataConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("sql/schema.sql")
                .addScript("sql/test-data.sql")
                .build();
    }

    @Bean
    public SqlSessionFactoryBean factoryBean(DataSource dataSource) {
        SqlSessionFactoryBean factoryBean =
                new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);

//        配置 mybatis 的其它细节
//        org.apache.ibatis.session.Configuration config =
//                new org.apache.ibatis.session.Configuration();
//        config.setLogImpl(Log4jImpl.class);
//        factoryBean.setConfiguration(config);
        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    // 将所有数据访问异常转换为 Spring 体系异常
//    @Bean
//    public BeanPostProcessor daoExceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }

}
