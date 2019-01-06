package spittr.dao;


import spittr.pojo.Spitter;

public interface SpitterDao {

  Spitter save(Spitter spitter);
  
  Spitter findByUsername(String username);

}
