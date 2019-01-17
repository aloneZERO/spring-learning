package spittr.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional
    public void count() {
        long count = spittleDao.count();
        assert count > 0;
        System.out.println(count);
    }

    @Test
    @Transactional
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
    @Transactional
    public void findRecentByCount() {
        List<Spittle> spittles = spittleDao.findRecent(2);
        assertNotNull(spittles);
        assertEquals(2, spittles.size());
        for (Spittle s: spittles) {
            assertNotNull(s);
            assertNotNull(s.getSpitter());
            printSpittle(s);
        }
    }

    @Test
    @Transactional
    public void findById() {
        Spittle spittle = spittleDao.findById(1235L).orElse(null);
        assertNotNull(spittle);
        assertNotNull(spittle.getSpitter());
        printSpittle(spittle);
    }

    @Test
    @Transactional
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
    @Transactional
    public void save() {
        Spitter spitter = spitterDao.findByUsername("leo");
        assertNotNull(spitter);

        Spittle spittle = new Spittle();
        spittle.setSpitter(spitter);
        spittle.setMessage("I am using JPA!");
        printSpittle(spittle);

        spittle = spittleDao.save(spittle);

        assertNotNull(spittle.getId());
        printSpittle(spittle);
    }

    @Test
    @Transactional
    public void delete() {
        long targetId = 1234L;
        assert spittleDao.existsById(targetId);
        spittleDao.deleteById(targetId);
        assert !spittleDao.existsById(targetId);
    }

    private void printSpittle(Spittle s) {
        System.out.println(s.getId()+", "+s.getSpitter().getUsername()+", "
                +s.getMessage()+", "+s.getPostedTime());
    }
}