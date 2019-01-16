package spittr.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import spittr.po.Spitter;

import java.util.List;

/**
 * @author justZero
 * @since 2019/1/15
 */
@Repository
public interface SpitterDao {

    @Select("select count(id) from spitter")
    long count();

    @Insert("insert into " +
            "spitter (id, username, password, fullName, email) " +
            "values (#{spitter.id}, #{spitter.username}, " +
            "#{spitter.password}, #{spitter.fullName}, #{spitter.username})")
    int add(@Param("spitter") Spitter spitter);

    @Update("update spitter set " +
            "username=#{spitter.username}, password=#{spitter.password}, " +
            "fullname=#{spitter.fullName}, email=#{spitter.email}, " +
            "updateByEmail=#{spitter.updateByEmail} where id=#{spitter.id}")
    int update(@Param("spitter") Spitter spitter);

    @Select("select " +
            "id, username, password, fullName, email, updateByEmail " +
            "from spitter where id=#{id}")
    Spitter findById(@Param("id") Integer id);

    @Select("select " +
            "id, username, password, fullName, email, updateByEmail " +
            "from spitter where username=#{username}")
    Spitter findByUsername(@Param("username") String username);

    @Select("select " +
            "id, username, password, fullName, email, updateByEmail " +
            "from spitter order by id")
    List<Spitter> findAll();
}
