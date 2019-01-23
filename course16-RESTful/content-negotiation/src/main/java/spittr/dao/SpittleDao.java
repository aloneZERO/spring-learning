package spittr.dao;

import spittr.pojo.Spittle;

import java.util.List;

/**
 * @author justZero
 * @since 2019/1/1
 */
public interface SpittleDao {

    List<Spittle> findRecentSpittles();

    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long id);

    void save(Spittle spittle);

}
