package spittr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Random;

/**
 * @author justZero
 * @since 2019/1/1
 */
@Data
@AllArgsConstructor
public class Spittle {

    private final Long id;
    private String message;
    private final Date time;
    private Double latitude;
    private Double longitude;

    private static final Random RANDOM = new Random();

    public Spittle(String message) {
        this(Math.abs(RANDOM.nextLong()), message, new Date(), null, null);
    }

    public Spittle(String message, Double latitude, Double longitude) {
        this(Math.abs(RANDOM.nextLong()), message, new Date(), latitude, longitude);
    }
}
