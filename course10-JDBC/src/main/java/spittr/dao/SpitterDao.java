package spittr.dao;

import spittr.po.Spitter;

import java.util.List;

/**
 * @author justZero
 * @since 2019/1/15
 */
public interface SpitterDao {

    long count();

    int add(Spitter spitter);

    int update(Spitter spitter);

    Spitter findOne(Integer id);

    Spitter findByUsername(String username);

    List<Spitter> findAll();
}
