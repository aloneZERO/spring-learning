package restclient;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import restclient.pojo.Spittle;

import java.util.*;

/**
 * REST 客户端
 * 可用来测试项目"message-converters"里的 Spittle API
 *
 * @author justZero
 * @since 2019/1/28
 */
@Component
@AllArgsConstructor
public class SpittleClient {

    private RestTemplate rest;

    private static final String ROOT_URL = "http://localhost/";

    public Spittle fetchSpittle(Long id) {
        ResponseEntity<Spittle> response = rest.getForEntity(
                ROOT_URL + "spittles/{id}",
                Spittle.class, id);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return null;
        }
        System.out.println("\n"+response.getStatusCode());
        return response.getBody();
    }

    /**
     * 由于 RestTemplate 原有方法不能直接解析 List 等复杂类型，
     * 所以先将数据以 JSON 字符串的形式接收，再利用 JSON 工具解析成集合对象。
     * 这里使用 fastjson
     */
    public List<Spittle> fetchSpittles(int count) {
        Map<String, Object> params = new HashMap<>();
        params.put("count", count);
        ResponseEntity<String> response = rest.getForEntity(
                ROOT_URL + "spittles",
                String.class, params);
        if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
            return new ArrayList<>();
        }
        System.out.println("\n"+response.getStatusCode());

        String json = response.getBody();
        return  JSON.parseArray(json, Spittle.class);
    }

    public String postSpittle(Spittle spittle) {
        String spittleJson = JSON.toJSONString(spittle);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(spittleJson, requestHeaders);
        ResponseEntity<Spittle> response = rest.postForEntity(
                ROOT_URL + "spittles",
                requestEntity, Spittle.class);

        if (response.getStatusCode() != HttpStatus.CREATED) {
            System.out.println("Spittle posting failed!");
            return null;
        }
        System.out.println("\n"+response.getBody());
        return response.getHeaders().getLocation().toString();
    }

    // 忽略其他响应头信息，只想要 Location
//    public String postSpittle(Spittle spittle) {
//        String spittleJson = JSON.toJSONString(spittle);
//        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//        HttpEntity<String> requestEntity = new HttpEntity<>(spittleJson, requestHeaders);
//        return rest.postForLocation(ROOT_URL + "spittles",
//                requestEntity, Spittle.class).toString();
//    }

}
