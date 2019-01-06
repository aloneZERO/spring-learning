package demo.concert;

import org.springframework.stereotype.Component;

/**
 * @author justZero
 * @since 2018/12/31
 */
@Component("newYear")
public class NewYearConcert implements Performance {

    @Override
    public void perform() {
        System.out.println("=== 新年演唱会 ===");
    }
}
