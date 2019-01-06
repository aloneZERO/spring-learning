package spittr.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spittr.dao.SpitterDao;
import spittr.exception.SpittleNotFoundException;
import spittr.pojo.Spitter;
import spittr.pojo.SpitterForm;
import spittr.utils.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@AllArgsConstructor
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterDao spitterDao;

    @PostMapping("/register")
    public Map processRegistration(
            @Valid SpitterForm spitterForm,
            Errors errors,
            @RequestParam(name = "avatar", required = false) MultipartFile avatarFile,
            HttpServletRequest request) throws IOException {
        Map<String, Object> map = new HashMap<>();
        Spitter spitter = spitterForm.toSpitter("default.png");

        if (errors.hasErrors()) {
            map.put("spitter", spitter);
            map.put("errors", simplifyErrors(errors));
            map.put(ResponseResult.KEY, ResponseResult.ERROR);
            return map;
        }

        if (avatarFile != null) {
            // 获取图片后缀，拼接成最终图片名
            String[] temp = avatarFile.getOriginalFilename().split("\\.");
            String suffix = temp[temp.length - 1];
            String fileName = spitterForm.getUsername() + System.currentTimeMillis() + suffix;
            spitter.setAvatar(fileName);
            String rootPath = request.getServletContext().getRealPath("/");
            avatarFile.transferTo(
                    new File(rootPath + "static/img/avatar/" + fileName));
        }

        spitterDao.save(spitter);
        map.put("spitter", spitter);
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

    /**
     * 生成简练的数据校验的错误信息
     * @param errors 全部错误
     * @param excludedFields 需要排除的字段
     * @return map
     */
    private Map simplifyErrors(Errors errors, String[] excludedFields) {
        Map<String, String> simpleErrors = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        List<String> excludedFieldList = Arrays.asList(excludedFields);
        for (FieldError fe: fieldErrors) {
            if (!excludedFieldList.contains(fe.getField())) {
                simpleErrors.put(fe.getField(), fe.getDefaultMessage());
            }
        }
        return simpleErrors;
    }
    private Map simplifyErrors(Errors errors) {
        Map<String, String> simpleErrors = new HashMap<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError fe: fieldErrors) {
            simpleErrors.put(fe.getField(), fe.getDefaultMessage());
        }
        return simpleErrors;
    }

}
