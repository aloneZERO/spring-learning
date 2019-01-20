package spittr.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import spittr.dao.SpittleDao;
import spittr.po.Spitter;
import spittr.po.Spittle;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author justZero
 * @since 2019-1-20
 */
@Repository
public class SpittleDaoImpl implements SpittleDao {

    private DataSource dataSource;
    private JdbcOperations jdbc;

    @Autowired
    public SpittleDaoImpl(DataSource dataSource, JdbcOperations jdbc) {
        this.dataSource = dataSource;
        this.jdbc = jdbc;
    }

    private static final String SELECT_SPITTLE = "select " +
                    "sp.id, s.id as spitterId, s.username, s.password, " +
                    "s.fullname, s.email, s.updateByEmail, sp.message, sp.postedTime " +
                    "from spittle sp, spitter s where sp.spitter = s.id ";

    private static final String SELECT_SPITTLE_BY_ID = SELECT_SPITTLE +
            " and sp.id=?";
    private static final String SELECT_SPITTLES_BY_SPITTER_ID = SELECT_SPITTLE +
            " and s.id=? order by sp.postedTime desc";
    private static final String SELECT_RECENT_SPITTLES = SELECT_SPITTLE +
            " order by sp.postedTime desc limit ?";

    private static final String COUNT_SPITTLE = "select count(id) from spittle";

    private static final String DELETE_SPITTLE_BY_ID = "delete from spittle where id=?";

    @Override
    public long count() {
        return jdbc.queryForObject(COUNT_SPITTLE, Long.class);
    }

    @Override
    public List<Spittle> findRecent() {
        return findRecent(10);
    }

    @Override
    @Cacheable("recentSpittles")
    public List<Spittle> findRecent(int count) {
        return jdbc.query(SELECT_RECENT_SPITTLES, new SpittleRowMapper(), count);
    }

    @Override
    @Cacheable("spittle")
    public Spittle findOne(long id) {
        return jdbc.queryForObject(SELECT_SPITTLE_BY_ID, new SpittleRowMapper(), id);
    }

    @Override
    @Cacheable("spittles")
    public List<Spittle> findBySpitterId(long spitterId) {
        return jdbc.query(SELECT_SPITTLES_BY_SPITTER_ID, new SpittleRowMapper(), spitterId);
    }

    @Override
    @CachePut(value = "spittle", key = "#result.id")
    public Spittle save(Spittle spittle) {
        long spittleId = insertSpittleAndReturnId(spittle);
        spittle.setId(spittleId);
        return spittle;
    }

    @Override
    @CacheEvict("spittle")
    public int delete(long id) {
       return jdbc.update(DELETE_SPITTLE_BY_ID, id);
    }

    private long insertSpittleAndReturnId(Spittle spittle) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(dataSource)
                .withTableName("spittle");
        jdbcInsert.setGeneratedKeyName("id");
        Map<String, Object> args = new HashMap<>();
        args.put("spitter", spittle.getSpitter().getId());
        args.put("message", spittle.getMessage());
        args.put("postedTime", spittle.getPostedTime());
        return jdbcInsert.executeAndReturnKey(args).longValue();
    }

    private static final class SpittleRowMapper implements RowMapper<Spittle> {
        public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
            Spitter spitter = new Spitter(
                    rs.getLong("spitterId"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("fullname"),
                    rs.getString("email"),
                    rs.getBoolean("updateByEmail"));
            return new Spittle(
                    rs.getLong("id"),
                    spitter,
                    rs.getString("message"),
                    rs.getTimestamp("postedTime"));
        }
    }
}
