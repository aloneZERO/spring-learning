package spittr.dao;

import spittr.po.Spitter;
import spittr.po.Spittle;

import java.util.List;

/**
 * @author justZero
 * @since 2019-1-20
 */
public interface SpitterDao {

    long count();

    Spitter save(Spitter spitter);

    Spitter findOne(Integer id);

    Spitter findByUsername(String username);

    List<Spitter> findAll();
}
