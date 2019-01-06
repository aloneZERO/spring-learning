package spittr.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
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
    private final String message;
    private final Date time;
    private Double latitude;
    private Double longitude;

    public Spittle(String message, Date time) {
        this(message, time, null, null);
    }

    public Spittle(String message, Date time, Double latitude, Double longitude) {
        this.id = Math.abs(new Random().nextLong());
        this.message = message;
        this.time = time;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
