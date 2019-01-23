package spittr.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spittr.data.SpittleDao;
import spittr.exception.SpittleNotFoundException;
import spittr.pojo.Error;
import spittr.pojo.Spittle;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.List;

/**
 * produces：指定返回值类型，同时还可以设定返回值的字符编码；
 * consumes：指定处理请求的提交内容类型（Content-Type）；仅处理指定内容类型的请求
 *
 * @author justZero
 * @since 2019-1-23
 */
@RestController
@RequestMapping("/spittles")
@AllArgsConstructor
public class SpittleApiController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    private SpittleDao spittleDao;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Spittle> spittles(
            @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
            @RequestParam(value = "count", defaultValue = "20") int count) {
        return spittleDao.findSpittles(max, count);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Spittle spittleById(@PathVariable Long id) {
        return spittleDao.findOne(id);
    }

    /**
     * 如果要处理 application/json 内容类型的请求，
     * 前端除了要设置 contentType 为 application/json 外，
     * 特别注意：发送的数据必须是 JSON 格式的字符串，字符串，字符串，重要的事情说三遍！
     * 只有这样 Spring MVC 才能解析成功。
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Spittle> saveSpittle(
            @RequestBody Spittle spittle,
            UriComponentsBuilder ucb) {
        System.out.println(spittle.getMessage());
        Spittle saved = spittleDao.save(spittle);

        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/spittles/")
                .path(saved.getId().toString())
                .build().toUri();
        headers.setLocation(locationUri);

        return new ResponseEntity<>(saved, headers, HttpStatus.CREATED);
    }

    @ExceptionHandler(SpittleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Error spittleNotFound(SpittleNotFoundException e) {
        long spittleId = e.getSpittleId();
        return new Error(4, "Spittle [" + spittleId + "] not found");
    }

}
