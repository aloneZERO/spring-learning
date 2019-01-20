package spittr.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import spittr.dao.SpitterDao;
import spittr.po.Spitter;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author justZero
 * @since 2019-1-20
 */
@Repository
public class SpitterDaoImpl implements SpitterDao {

    private DataSource dataSource;
    private JdbcOperations jdbc;

    @Autowired
    public SpitterDaoImpl(DataSource dataSource, JdbcOperations jdbc) {
        this.dataSource = dataSource;
        this.jdbc = jdbc;
    }

    private static final String COUNT_SPITTER = "select count(id) from spitter";

    private static final String UPDATE_SPITTER =
            "update spitter set username=?, password=?, fullname=?, email=?, updateByEmail=? where id=?";

    private static final String SELECT_SPITTER_BY_ID =
            "select id, username, password, fullName, email, updateByEmail from spitter where id=?";

    private static final String SELECT_SPITTER_BY_USERNAME =
            "select id, username, password, fullName, email, updateByEmail from spitter where username=?";

    private static final String SELECT_ALL_SPITTER =
            "select id, username, password, fullName, email, updateByEmail from spitter order by id";

    @Override
    public long count() {
        return jdbc.queryForObject(COUNT_SPITTER, Long.class);
    }

    @Override
    @CachePut(value = "spitterCache", key = "#result.id")
    public Spitter save(Spitter spitter) {
        if (spitter.getId() == null) {
            Long id = insertAndReturnId(spitter);
            spitter.setId(id);
        } else {
            jdbc.update(UPDATE_SPITTER,
                    spitter.getId(),
                    spitter.getUsername(),
                    spitter.getPassword(),
                    spitter.getFullName(),
                    spitter.getEmail());
        }
        return spitter;
    }

    @Override
    public Spitter findOne(Integer id) {
        return jdbc.queryForObject(SELECT_SPITTER_BY_ID,
                (rs, rowNum) -> new Spitter(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("fullName"),
                    rs.getString("email"),
                    rs.getBoolean("updateByEmail") ), id);
    }

    @Override
    public Spitter findByUsername(String username) {
        return jdbc.queryForObject(
                SELECT_SPITTER_BY_USERNAME,
                new SpitterRowMapper(),
                username);
    }

    @Override
    public List<Spitter> findAll() {
        return jdbc.query(SELECT_ALL_SPITTER, new SpitterRowMapper());
    }

    private long insertAndReturnId(Spitter spitter) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("spitter");
        jdbcInsert.setGeneratedKeyName("id");
        Map<String, Object> args = new HashMap<>();
        args.put("username", spitter.getUsername());
        args.put("password", spitter.getPassword());
        args.put("fullname", spitter.getFullName());
        args.put("email", spitter.getEmail());
        args.put("updateByEmail", spitter.isUpdateByEmail());
        return jdbcInsert.executeAndReturnKey(args).longValue();
    }

    private static final class SpitterRowMapper implements RowMapper<Spitter> {
        public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spitter(
                    rs.getLong("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getBoolean("updateByEmail"));
        }
    }
}
