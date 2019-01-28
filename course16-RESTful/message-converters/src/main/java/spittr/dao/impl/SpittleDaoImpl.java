package spittr.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import spittr.dao.SpittleDao;
import spittr.exception.SpittleNotFoundException;
import spittr.pojo.Spittle;

import javax.sql.DataSource;

@Repository
@AllArgsConstructor
public class SpittleDaoImpl implements SpittleDao {

    private JdbcOperations jdbc;

    private DataSource dataSource;

    @Override
    public List<Spittle> findRecent() {
        return jdbc.query(
                "SELECT id, message, created_at, latitude, longitude" +
                        " FROM Spittle" +
                        " ORDER BY created_at DESC LIMIT 20",
                new SpittleRowMapper());
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return jdbc.query(
                "SELECT id, message, created_at, latitude, longitude" +
                        " FROM Spittle" +
                        " WHERE id < ?" +
                        " ORDER BY created_at DESC LIMIT 20",
                new SpittleRowMapper(), max);
    }

    @Override
    public Spittle findOne(long id) {
        try {
            return jdbc.queryForObject(
                    "SELECT id, message, created_at, latitude, longitude" +
                            " FROM Spittle" +
                            " WHERE id = ?",
                    new SpittleRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            throw new SpittleNotFoundException(id);
        }
    }

    @Override
    public Spittle save(Spittle spittle) {
        Long id = insertAndReturnId(spittle);
        spittle.setId(id);
        return spittle;
    }

    private long insertAndReturnId(Spittle spittle) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("spittle");
        jdbcInsert.setGeneratedKeyName("id");
        Map<String, Object> args = new HashMap<>();
        args.put("message", spittle.getMessage());
        args.put("created_at", spittle.getTime());
        args.put("latitude", spittle.getLatitude());
        args.put("longitude", spittle.getLongitude());
        return jdbcInsert.executeAndReturnKey(args).longValue();
    }

    private static class SpittleRowMapper implements RowMapper<Spittle> {
        public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Spittle(
                    rs.getLong("id"),
                    rs.getString("message"),
                    rs.getDate("created_at"),
                    rs.getDouble("longitude"),
                    rs.getDouble("latitude"));
        }
    }

}
