package spittr.data;

import java.util.List;

import spittr.pojo.Spittle;

public interface SpittleDao {

  List<Spittle> findRecent();

  List<Spittle> findSpittles(long max, int count);
  
  Spittle findOne(long id);

  Spittle save(Spittle spittle);

}
