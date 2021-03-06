package spittr.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spittr.config.DataConfig;
import spittr.dao.SpitterDao;
import spittr.dao.SpittleDao;
import spittr.po.Spitter;
import spittr.po.Spittle;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
public class SpittleDaoTest {

    @Autowired
    private SpittleDao spittleDao;

    @Autowired
    private SpitterDao spitterDao;

    @Test
    public void count() {
        long count = spittleDao.count();
        assert count > 0;
        System.out.println(count);
    }

    @Test
    public void findRecent() {
        List<Spittle> spittles = spittleDao.findRecent();
        assertNotNull(spittles);
        assert spittles.size() > 0;
        for (Spittle s: spittles) {
            assertNotNull(s);
            assertNotNull(s.getSpitter());
            printSpittle(s);
        }
    }

    @Test
    public void findRecentByCount() {
        List<Spittle> spittles = spittleDao.findRecentByCount(2);
        assertNotNull(spittles);
        assertEquals(2, spittles.size());
        for (Spittle s: spittles) {
            assertNotNull(s);
            assertNotNull(s.getSpitter());
            printSpittle(s);
        }
    }

    @Test
    public void findById() {
        Spittle spittle = spittleDao.findById(1235);
        assertNotNull(spittle);
        printSpittle(spittle);
        assertNotNull(spittle.getSpitter());
    }

    @Test
    public void findBySpitterId() {
        List<Spittle> spittles = spittleDao.findBySpitterId(2333);
        assertNotNull(spittles);
        assert spittles.size() > 0;
        for (Spittle s: spittles) {
            assertNotNull(s);
            assertNotNull(s.getSpitter());
            printSpittle(s);
        }
    }

    @Test
    public void save() {
        Spitter spitter = spitterDao.findByUsername("leo");
        Spittle spittle = new Spittle();
        spittle.setSpitter(spitter);
        spittle.setMessage("I am using mybatis!");
        printSpittle(spittle);

        int res = spittleDao.save(spittle);

        assertEquals(1, res);
        assertNotNull(spittle.getId());
        printSpittle(spittle);
    }

    @Test
    public void delete() {
        int res = spittleDao.delete(1234);
        assertEquals(1, res);
    }

    private void printSpittle(Spittle s) {
        System.out.println(s.getId()+", "+s.getSpitter().getUsername()+", "
                +s.getMessage()+", "+s.getPostedTime());
    }
}