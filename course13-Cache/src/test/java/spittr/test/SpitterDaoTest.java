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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 用户的操作频率低，局部性特征不强
 * 没有使用缓存的必要
 *
 * @author justZero
 * @since 2019-1-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class SpitterDaoTest {

    @Autowired
    private SpitterDao spitterDao;

    @Test
    public void testFindOne() {
        Spitter spitter = spitterDao.findOne(1);
        assertNotNull(spitter);
        System.out.println(spitter);
        assertEquals(1, spitter.getId().intValue());
    }

    @Test
    @Transactional
    public void testSave() {
        Spitter spitter = new Spitter();
        spitter.setUsername("test");
        spitter.setPassword("233");
        spitter.setFullName("I'm test.");
        spitter.setEmail("ttt@test.com");
        spitter = spitterDao.save(spitter);
        assertNotNull(spitter.getId());
        System.out.println(spitter);
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
        assert count > 0;
        System.out.println(count);
    }

    @Test
    public void testFindAll() {
        List<Spitter> spitters = spitterDao.findAll();
        assertNotNull(spitters);
        assert spitters.size() > 0;
        spitters.forEach(System.out::println);
    }

}
