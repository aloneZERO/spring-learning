package spittr.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;

/**
 * 测试数据源 profile 的激活情况
 *
 * @author justZero
 * @since 2019/1/14
 */
@Slf4j
@Controller
@AllArgsConstructor
public class TestController {

    private DataSource dataSource;

    @GetMapping("/ds")
    public String test(Model model) {
        log.info("当前数据源：{}", dataSource);
        model.addAttribute("message", dataSource.toString());
        return "message";
    }

}
