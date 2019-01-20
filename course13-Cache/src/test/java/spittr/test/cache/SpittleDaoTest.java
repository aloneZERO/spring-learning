package spittr.test.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import spittr.config.DataConfig;
import spittr.config.cache.RedisCacheConfig;
import spittr.dao.SpitterDao;
import spittr.dao.SpittleDao;
import spittr.po.Spitter;
import spittr.po.Spittle;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * 启动缓存
 *
 * @author justZero
 * @since 2019-1-20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        DataConfig.class,
        RedisCacheConfig.class
})
public class SpittleDaoTest {

    @Autowired
    private SpittleDao spittleDao;

    @Autowired
    private SpitterDao spitterDao;

    @Test
    @Transactional
    public void save() {
        Spitter spitter = spitterDao.findByUsername("leo");
        assertNotNull(spitter);
        Spittle spittle = new Spittle(spitter, "Test message~");
        spittle = spittleDao.save(spittle);
        assertNotNull(spittle.getId());
    }

    @Test
    public void count() {
        long count = spittleDao.count();
        assert count > 0;
        System.out.println(count);
    }

    @Test
    @Repeat(100)
    public void findRecent() {
        List<Spittle> spittles = spittleDao.findRecent(2);
        assertEquals(2, spittles.size());
        spittles.forEach(System.out::println);
    }

    @Test
    @Repeat(100)
    public void findOne() {
        Spittle spittle = spittleDao.findOne(1);
        assertNotNull(spittle);
        System.out.println(spittle);
    }

    @Test
    @Repeat(100)
    public void findBySpitterId() {
        List<Spittle> spittles = spittleDao.findBySpitterId(1);
        assert spittles.size() > 0;
        spittles.forEach(System.out::println);
    }
}