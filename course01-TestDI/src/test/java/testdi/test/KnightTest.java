package testdi.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import testdi.io.FakePrintStream;
import testdi.knight.Knight;

/**
 * 测试依赖注入和面向切面编程
 *
 * @author justZero
 * @since 2018/12/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/minstrel.xml")
public class KnightTest {

    @Autowired
    private Knight knight;

    @Autowired
    FakePrintStream printStream;

    // 每个测试方法执行后清空输入流
    @After
    public void clearup() {
        printStream.clear();
    }

    @Test
    public void testKnightWithMinstrel() {
        knight.embarkOnQuest();
        Assert.assertEquals(
                "Fa fa fa, 骑士异常勇敢!\n" +
                        "开始探险去诛杀恶龙!\n" +
                        "勇敢的骑士结束了冒险!\n",
                printStream.getPrintedString());

        System.out.print("\n"+printStream.getPrintedString());
    }
}
