package restclient.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import restclient.config.RootConfig;
import restclient.pojo.Spittle;
import restclient.SpittleClient;

import java.net.URI;
import java.util.Date;
import java.util.List;

/**
 * @author justZero
 * @since 2019/1/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class SpittleClientTest {

    @Autowired
    private SpittleClient client;

    @Test
    public void testFetchSpittle() {
        Spittle spittle = client.fetchSpittle(1234L);
        assertNotNull(spittle);
        assert spittle.getId()==1234L;
        System.out.println(spittle);
    }

    @Test
    public void testFetchSpittles() {
        List<Spittle> spittles = client.fetchSpittles(10);
        assert spittles.size()>0;
        spittles.forEach(System.out::println);
    }

    @Test
    public void testPostSpittle() {
        Spittle spittle = new Spittle("I'm from REST client.");
        String location = client.postSpittle(spittle);
        System.out.println(location);
    }
}
