package spittr.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import spittr.config.DataConfig;
import spittr.config.WebConfig;
import spittr.dao.SpittleDao;
import spittr.po.Spittle;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {
        WebConfig.class, DataConfig.class
    })
public class SpittleDaoTest {

    @Autowired
    private SpittleDao spittleDao;

    @Test
    public void count() {
        long count = spittleDao.count();
        assert count > 0;
        System.out.println(count);
    }

    @Test
    public void findRecent() {
        List<Spittle> spittles = spittleDao.findRecent(1);
        assertNotNull(spittles);
        assertEquals(1, spittles.size());
        System.out.println(spittles);
    }

    @Test
    public void findOne() {
        Spittle spittle = spittleDao.findOne(1234);
        assertNotNull(spittle);
        System.out.println(spittle);
    }

    @Test
    public void findBySpitterId() {
        List<Spittle> spittles = spittleDao.findBySpitterId(2333);
        assertNotNull(spittles);
        assert spittles.size() > 0;
        System.out.println(spittles);
    }
}