package spittr.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 该异常映射为HTTP 404状态
 */
@NoArgsConstructor
@ResponseStatus(value = HttpStatus.NOT_FOUND,
        reason = "Spittle Not Found")
public class SpittleNotFoundException extends RuntimeException {

    public SpittleNotFoundException(String message) {
        super(message);
    }

}
