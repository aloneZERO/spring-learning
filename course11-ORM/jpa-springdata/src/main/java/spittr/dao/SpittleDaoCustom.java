package spittr.dao;

import spittr.po.Spittle;

import java.util.List;

/**
 * @author justZero
 * @since 2019/1/17
 */
public interface SpittleDaoCustom {

    List<Spittle> findRecent();

    List<Spittle> findRecent(int count);

}
