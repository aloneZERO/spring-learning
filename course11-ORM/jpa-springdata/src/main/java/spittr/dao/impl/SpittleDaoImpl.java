package spittr.dao.impl;

import spittr.dao.SpittleDaoCustom;
import spittr.po.Spittle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author justZero
 * @since 2019/1/17
 */
public class SpittleDaoImpl implements SpittleDaoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Spittle> findRecent() {
        return findRecent(10);
    }

    @Override
    public List<Spittle> findRecent(int count) {
        return (List<Spittle>) entityManager
                .createQuery("select s from spittle s order by s.postedTime desc")
                .setMaxResults(count)
                .getResultList();
    }
}
