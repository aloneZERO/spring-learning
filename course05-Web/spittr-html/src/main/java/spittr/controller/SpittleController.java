package spittr.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spittr.dao.SpittleDao;
import spittr.pojo.Spittle;
import spittr.pojo.SpittleForm;
import spittr.utils.ResponseResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/spittles")
public class SpittleController {

    private static final String MAX_LONG = "9223372036854775807";

    private SpittleDao spittleDao;

    @GetMapping("/find")
    public Map spittles(
            @RequestParam(value = "max", defaultValue = MAX_LONG) long max,
            @RequestParam(value = "count", defaultValue = "20") int count) {
        Map<String, Object> map = new HashMap<>();

        List<Spittle> spittles = spittleDao.findSpittles(max, count);
        if (spittles != null) {
            map.put("spittleList", spittles);
            map.put(ResponseResult.KEY, ResponseResult.SUCCESS);
        } else {
            map.put(ResponseResult.KEY, ResponseResult.ERROR);
        }
        return map;
    }

    @PostMapping("/save")
    public Map saveSpittle(SpittleForm form) throws Exception {
        log.info("{}", form);
        Map<String, Object> map = new HashMap<>();

        Spittle spittle = new Spittle(form.getMessage(), new Date(),
                form.getLatitude(), form.getLatitude());
        spittleDao.save(spittle);
        map.put("spittle", spittle);
        map.put(ResponseResult.KEY, ResponseResult.SUCCESS);
        return map;
    }

    @GetMapping("/{spittleId}")
    public Map showSpittle(
            @PathVariable("spittleId") long spittleId) {
        Map<String, Object> map = new HashMap<>();

        Spittle spittle = spittleDao.findOne(spittleId);
        if (spittle != null) {
            map.put("spittle", spittle);
            map.put(ResponseResult.KEY, ResponseResult.SUCCESS);
        } else {
            map.put(ResponseResult.KEY, ResponseResult.ERROR);
        }
        return map;
    }

}
