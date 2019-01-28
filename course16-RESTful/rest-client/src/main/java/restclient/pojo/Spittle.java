package restclient.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author justZero
 * @since 2019/1/1
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spittle {

    private Long id;
    private String message;
    private Date time;
    private Double latitude;
    private Double longitude;

    public Spittle(String message) {
        this(null, message, new Date(), null, null);
    }
}
