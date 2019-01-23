package spittr.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class SpittleForm {

    @NotBlank(message = "{spittle.notblank}")
    @Size(min = 1, max = 140, message = "{spittle.size}")
    private String message;

    @Range(min = -90, max = 90, message = "{latitude.check}")
    private Double latitude;

    @Range(min = -180, max = 180, message = "{longitude.check}")
    private Double longitude;

    public Spittle toSpittle() {
        return new Spittle(message, latitude, longitude);
    }
}
