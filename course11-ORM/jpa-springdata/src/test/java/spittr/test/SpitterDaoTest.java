package spittr.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import spittr.config.DataConfig;
import spittr.dao.SpitterDao;
import spittr.po.Spitter;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author justZero
 * @since 2019/1/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class SpitterDaoTest {

    @Autowired
    private SpitterDao spitterDao;

    @Test
    @Transactional
    public void testFindById() {
        Spitter spitter = spitterDao.findById(2333L).orElse(null);
        assertNotNull(spitter);
        System.out.println(spitter);
        assertEquals(2333, spitter.getId().intValue());
    }

    @Test
    @Transactional
    public void testInsert() {
        Spitter spitter = new Spitter();
        spitter.setUsername("mybatis");
        spitter.setPassword("233");
        spitter.setFullName("I'm mybatis.");
        spitter.setEmail("mybatis@test.com");
        spitter = spitterDao.save(spitter);
        assertNotNull(spitter);
        System.out.println(spitter);
        assertNotNull(spitter.getId());
    }

    @Test
    @Transactional
    public void testFindByUsername() {
        Spitter spitter = spitterDao.findByUsername("zero");
        assertNotNull(spitter);
        System.out.println(spitter);
        assertEquals("zero", spitter.getUsername());
    }

    @Test
    @Transactional
    public void testCount() {
        long count = spitterDao.count();
        System.out.println(count);
        assert count > 0;
    }

    @Test
    @Transactional
    public void testFindAll() {
        List<Spitter> spitters = spitterDao.findAll();
        assertNotNull(spitters);
        assert spitters.size() > 0;
        System.out.println(spitters);
    }

    @Test
    @Transactional
    public void testFindAllQQMail() {
        List<Spitter> spitters = spitterDao.findAllQQMailSpitters();
        assertNotNull(spitters);
        assert spitters.size() > 0;
        for (Spitter s: spitters) {
            assert s.getEmail().contains("qq.com");
            System.out.println(s.getUsername()+": "+s.getEmail());
        }
    }

}
