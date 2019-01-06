package spittr.utils;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 响应信息的结果集
 * 枚举类型解析为JSON默认仅为对象名，如SUCCESS；
 * 使用@JsonFormat(shape = JsonFormat.Shape.OBJECT)，
 * 让其以普通对象形式解析，即可得到理想的效果。
 *
 * @author justZero
 * @since 2019/1/2
 */
@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResponseResult {

    ERROR(0, "error"),
    SUCCESS(1, "success");

    private final Integer code;
    private final String message;

    public static final String KEY = "result";

}
