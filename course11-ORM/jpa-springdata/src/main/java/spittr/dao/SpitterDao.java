package spittr.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spittr.po.Spitter;

import java.util.List;

/**
 * @author justZero
 * @since 2019/1/15
 */
@Repository
public interface SpitterDao extends JpaRepository<Spitter, Long> {

    Spitter findByUsername(String username);

    // 使用 Hibernate HQL
    @Query("select s from spitter s where s.email like '%qq.com'")
    List<Spitter> findAllQQMailSpitters();

    // 使用原生 SQL
//    @Query(value = "select id, username, password, fullName, email, updateByEmail " +
//            "from spitter where email like '%qq.com'", nativeQuery = true)
//    List<Spitter> findAllQQMailSpitters();

}
