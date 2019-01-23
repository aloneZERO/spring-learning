package spittr.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spittr.dao.SpittleDao;
import spittr.pojo.Spittle;
import spittr.pojo.SpittleForm;

import java.util.List;

@Controller
@RequestMapping("/spittles")
@AllArgsConstructor
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private SpittleDao spittleDao;

    @GetMapping
    public List<Spittle> spittles(
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = "20") int count) {
        return spittleDao.findSpittles(max, count);
    }

    @GetMapping("/{spittleId}")
    public String spittle(
            @PathVariable("spittleId") long spittleId,
            Model model) {
        model.addAttribute(spittleDao.findOne(spittleId));
        return "spittle";
    }

    @PostMapping
    public String saveSpittle(SpittleForm form) {
        spittleDao.save(form.toSpittle());
        return "redirect:/spittles";
    }

}
