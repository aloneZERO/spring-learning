package spittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注解@Controller等价于@Component，只是前者可读性更好。
 *
 * 注解@GetMapping等价于@RequestMapping(value="xxx", method=RequestMethod.GET)；
 * 类似的 PostMapping 同理。
 *
 * @author justZero
 * @since 2019/1/1
 */
@Controller
@RequestMapping({"/", "/homepage", "/index"})
public class HomeController {

    @GetMapping
    public String home() {
        return "home";
    }

}
