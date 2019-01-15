package spittr.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import spittr.config.DataConfig;
import spittr.config.WebConfig;
import spittr.dao.SpitterDao;
import spittr.po.Spitter;

import java.util.List;

/**
 * @author justZero
 * @since 2019/1/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        DataConfig.class, WebConfig.class
    })
public class SpitterDaoTest {

    @Autowired
    private SpitterDao spitterDao;

    @Test
    public void testFindOne() {
        Spitter spitter = spitterDao.findOne(2333);
        assertNotNull(spitter);
        System.out.println(spitter);
        assertEquals(2333, spitter.getId().intValue());
    }

    @Test
    public void testInsert() {
        Spitter spitter = new Spitter();
        spitter.setUsername("test");
        spitter.setPassword("233");
        spitter.setFullName("I'm test.");
        spitter.setEmail("ttt@test.com");
        int res = spitterDao.add(spitter);
        assertEquals(1, res);
    }

    @Test
    public void testFindByUsername() {
        Spitter spitter = spitterDao.findByUsername("zero");
        assertNotNull(spitter);
        System.out.println(spitter);
        assertEquals("zero", spitter.getUsername());
    }

    @Test
    public void testCount() {
        long count = spitterDao.count();
        System.out.println(count);
        assert count > 0;
    }

    @Test
    public void testFindAll() {
        List<Spitter> spitters = spitterDao.findAll();
        assertNotNull(spitters);
        assert spitters.size() > 0;
        System.out.println(spitters);
    }

}
