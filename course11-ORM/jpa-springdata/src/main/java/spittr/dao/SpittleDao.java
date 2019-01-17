package spittr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spittr.po.Spittle;

import java.util.List;

/**
 * @author justZero
 * @since 2019/1/15
 */
@Repository
public interface SpittleDao
        extends JpaRepository<Spittle, Long>, SpittleDaoCustom {

    List<Spittle> findBySpitterId(long spitterId);

}
