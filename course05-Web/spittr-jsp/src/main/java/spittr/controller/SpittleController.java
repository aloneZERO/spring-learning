package spittr.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spittr.dao.SpittleDao;
import spittr.pojo.Spittle;
import spittr.pojo.SpittleForm;

import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/spittles")
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private SpittleDao spittleDao;

    /**
     * 返回值为String时，跳转到对应的视图；
     * 返回值为ModelAndView时，跳转视图的同时，也会携带数据。
     *
     * 若返回值为集合或其它非String对象时，该值会被放到Model中，
     * Model属性中的key根据其类型自动推断。逻辑视图的名称则根据
     * 请求路径推断得到。
     */
    @GetMapping
    public ModelAndView spittles(
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = "6") int count) {
        List<Spittle> spittles = spittleDao.findSpittles(max, count);
        ModelAndView mv = new ModelAndView("spittles");
        mv.addObject("spittleList", spittles);

        log.info("max = {}", max);
        log.info("Spittles: {}", spittles);
        return mv;
    }

    @PostMapping
    public String saveSpittle(SpittleForm form, Model model) throws Exception {
        log.info("{}", form);

        spittleDao.save(
                new Spittle(form.getMessage(), new Date(),
                        form.getLatitude(), form.getLatitude()));
        return "redirect:/spittles";
    }

    @GetMapping("/{spittleId}")
    public String showSpittle(
            @PathVariable("spittleId") long spittleId,
            Model model) {
        model.addAttribute("spittle", spittleDao.findOne(spittleId));
        return "spittle";
    }

}
