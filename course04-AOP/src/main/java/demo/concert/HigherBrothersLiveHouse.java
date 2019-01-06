package demo.concert;

import org.springframework.stereotype.Component;

/**
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
