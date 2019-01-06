package spittr.dao.impl;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import spittr.dao.SpittleDao;
import spittr.pojo.Spittle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@AllArgsConstructor
public class SpittleDaoJdbcImpl implements SpittleDao {

    private JdbcOperations jdbc;

    @Override
    public List<Spittle> findRecentSpittles() {
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
                        " WHERE id <= ?" +
                        " ORDER BY created_at DESC LIMIT 20",
                new SpittleRowMapper(), max);
    }

    @Override
    public Spittle findOne(long id) {
        return jdbc.queryForObject(
                "SELECT id, message, created_at, latitude, longitude" +
                        " FROM Spittle" +
                        " WHERE id = ?",
                new SpittleRowMapper(), id);
    }

    @Override
    public void save(Spittle spittle) {
        jdbc.update(
                "INSERT INTO Spittle (id, message, created_at, latitude, longitude)" +
                        " VALUES (?, ?, ?, ?, ?)",
                spittle.getId(),
                spittle.getMessage(),
                spittle.getTime(),
                spittle.getLatitude(),
                spittle.getLongitude());
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
