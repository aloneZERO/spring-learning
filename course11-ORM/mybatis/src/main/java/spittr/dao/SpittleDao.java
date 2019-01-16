package spittr.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import spittr.po.Spittle;

import java.util.List;

/**
 * 注解 @Results 替代 Mapper.xml 中的 &lt;resultMap&gt; 标签，
 * 这里提供了两种复杂结果集和对象的映射思路：依自己喜好使用吧
 *  1. 多条简单 SQL 实现；
 *  2. 一条复杂 SQL 和复杂 @Results 实现。
 *
 * @author justZero
 * @since 2019/1/15
 */
@Repository
public interface SpittleDao {

    @Select("select count(id) from spittle")
    long count();

    @Select("select " +
            "id, spitter as spitterId, message, postedTime " +
            "from spittle " +
            "ORDER BY postedTime DESC LIMIT 10")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "spitter", column = "spitterId",
                  one = @One(select = "spittr.dao.SpitterDao.findById"))
    })
    List<Spittle> findRecent();

    @Select("select " +
            "sp.id, s.id as spitterId, s.username, s.fullname, " +
            "s.email, s.updateByEmail, sp.message, sp.postedTime " +
            "from spittle sp, spitter s where sp.spitter = s.id " +
            "ORDER BY sp.postedTime DESC LIMIT #{count}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "spitter.id", column = "spitterId"),
            @Result(property = "spitter.username", column = "username"),
            @Result(property = "spitter.fullName", column = "fullName"),
            @Result(property = "spitter.email", column = "email"),
            @Result(property = "spitter.updateByEmail", column = "updateByEmail")
    })
    List<Spittle> findRecentByCount(@Param("count") int count);

    @Select("select " +
            "sp.id, s.id as spitterId, s.username, s.fullname, " +
            "s.email, s.updateByEmail, sp.message, sp.postedTime " +
            "from spittle sp, spitter s where sp.spitter = s.id and sp.ID=#{id}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "spitter.id", column = "spitterId"),
            @Result(property = "spitter.username", column = "username"),
            @Result(property = "spitter.fullName", column = "fullName"),
            @Result(property = "spitter.email", column = "email"),
            @Result(property = "spitter.updateByEmail", column = "updateByEmail")
    })
    Spittle findById(@Param("id") long id);

    @Insert("INSERT INTO spittle (spitter, message, postedTime) VALUES " +
            "(#{sp.spitter.id}, #{sp.message}, #{sp.postedTime})")
    @SelectKey(statement = "CALL identity()", keyProperty = "sp.id",
            before = false, resultType = long.class)
    int save(@Param("sp") Spittle spittle);

    @Select("select " +
            "sp.id, s.id as spitterId, s.username, s.fullname, " +
            "s.email, s.updateByEmail, sp.message, sp.postedTime " +
            "from spittle sp, spitter s where sp.spitter = s.id and " +
            "s.id = #{sid} ORDER BY sp.postedTime desc")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "spitter.id", column = "spitterId"),
            @Result(property = "spitter.username", column = "username"),
            @Result(property = "spitter.fullName", column = "fullName"),
            @Result(property = "spitter.email", column = "email"),
            @Result(property = "spitter.updateByEmail", column = "updateByEmail")
    })
    List<Spittle> findBySpitterId(@Param("sid") long spitterId);

    @Delete("delete from spittle where id=#{id}")
    int delete(@Param("id") long id);

}
