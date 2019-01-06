package spittr.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import spittr.dao.SpitterDao;
import spittr.pojo.Spitter;
import spittr.utils.ResponseResult;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterDao spitterDao;

    @PostMapping("/register")
    public Map processRegistration(
            @Valid Spitter spitter, Errors errors) {
        Map<String, Object> map = new HashMap<>();
        map.put("spitter", spitter);

        if (errors.hasErrors()) {
            map.put("errors", simplifyErrors(errors));
            map.put(ResponseResult.KEY, ResponseResult.ERROR);
            return map;
        }

        spitterDao.save(spitter);
        map.put(ResponseResult.KEY, ResponseResult.SUCCESS);
        return map;
    }

    @GetMapping("/{username}")
    public Map showSpitterProfile(
            @PathVariable("username") String username) {
        Map<String, Object> map = new HashMap<>();

        Spitter spitter = spitterDao.findByUsername(username);
        if (spitter != null) {
            map.put("spitter", spitter);
            map.put(ResponseResult.KEY, ResponseResult.SUCCESS);
        } else {
            map.put(ResponseResult.KEY, ResponseResult.ERROR);
        }
        return map;
    }

    // 生成简练的数据校验的错误信息
    private Map simplifyErrors(Errors errors) {
        Map<String, String> simpleErrors = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError fe: fieldErrors) {
            simpleErrors.put(fe.getField(), fe.getDefaultMessage());
        }
        return simpleErrors;
    }

}
