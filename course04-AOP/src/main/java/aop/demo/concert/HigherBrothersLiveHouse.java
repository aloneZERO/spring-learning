package aop.demo.concert;

import org.springframework.stereotype.Component;

/**
 * Higher Brothers 的演出
 * https://baike.baidu.com/item/Higher%20Brothers/22422299
 *
 * @author justZero
 * @since 2018/12/31
 */
@Component("higherBrothers")
public class HigherBrothersLiveHouse implements Performance {

    @Override
    public void perform() {
        System.out.println("=== HIGHER BROTHERS ===");
    }
}
