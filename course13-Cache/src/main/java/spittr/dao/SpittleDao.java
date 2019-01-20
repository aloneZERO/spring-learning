package spittr.dao;

import spittr.po.Spittle;

import java.util.List;

/**
 * @author justZero
 * @since 2019-1-20
 */
public interface SpittleDao {

    long count();

    List<Spittle> findRecent();

    List<Spittle> findRecent(int count);

    Spittle findOne(long id);

    Spittle save(Spittle spittle);

    List<Spittle> findBySpitterId(long spitterId);

    int delete(long id);

}
