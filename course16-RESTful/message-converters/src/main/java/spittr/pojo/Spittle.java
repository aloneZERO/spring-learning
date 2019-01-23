package spittr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author justZero
 * @since 2019/1/1
 */
@Data
@AllArgsConstructor
public class Spittle {

    private Long id;
    private String message;
    private final Date time;
    private Double latitude;
    private Double longitude;

    public Spittle() {
        this.time = new Date();
    }

    public Spittle(String message) {
        this(null, message, new Date(), null, null);
    }

    public Spittle(String message, Double latitude, Double longitude) {
        this(null, message, new Date(), latitude, longitude);
    }
}
