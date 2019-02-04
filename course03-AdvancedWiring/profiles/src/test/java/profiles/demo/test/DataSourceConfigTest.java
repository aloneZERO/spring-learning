package profiles.demo.test;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import profiles.demo.DataSourceConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 用 @ActiveProfiles 激活 Profiles
 *
 * @author justZero
 * @since 2018-12-29
 */
public class DataSourceConfigTest {

    // java 文件配置测试

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = DataSourceConfig.class)
    @ActiveProfiles("dev")
    public static class DevDataSourceTest {
        @Autowired
        private DataSource dataSource;

        @Test
        public void shouldBeEmbeddedDatasource() {
            assertNotNull(dataSource);
            JdbcTemplate jdbc = new JdbcTemplate(dataSource);
            List<String> results = jdbc.query("SELECT id, name FROM Things",
                    (rs, rowNum) ->
                            rs.getLong("id") + ":" +
                            rs.getString("name"));

            assertEquals(1, results.size());
            assertEquals("1:A", results.get(0));
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration(classes = DataSourceConfig.class)
    @ActiveProfiles("prod")
    public static class ProductionDataSourceTest {
        @Autowired
        private DataSource dataSource;

        @Test
        public void shouldBeEmbeddedDatasource() {
            // 因为这里没有在 JNDI 里配置数据源
            assertNull(dataSource);
        }
    }

    // XML 配置测试

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:datasource-config.xml")
    @ActiveProfiles("dev")
    public static class DevDataSourceTest_XMLConfig {
        @Autowired
        private DataSource dataSource;

        @Test
        public void shouldBeEmbeddedDatasource() {
            assertNotNull(dataSource);
            JdbcTemplate jdbc = new JdbcTemplate(dataSource);
            List<String> results = jdbc.query(
                    // java8 推荐使用 lambda 表达式代替匿名类
                    "SELECT id, name FROM Things", new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                    return rs.getLong("id") + ":" + rs.getString("name");
                }
            });

            assertEquals(1, results.size());
            assertEquals("1:A", results.get(0));
        }
    }

    @RunWith(SpringJUnit4ClassRunner.class)
    @ContextConfiguration("classpath:datasource-config.xml")
    @ActiveProfiles("prod")
    public static class ProductionDataSourceTest_XMLConfig {
        @Autowired(required = false)
        private DataSource dataSource;

        @Test
        public void shouldBeEmbeddedDatasource() {
            // 因为这里没有在 JNDI 里配置数据源
            assertNull(dataSource);
        }
    }

}
