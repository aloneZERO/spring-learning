package spittr.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
public class Spittle implements Serializable {

    private static final long serialVersionUID = 3851421237037355228L;

    private Long id;
    private Spitter spitter;
    private String message;
    private Date postedTime;

    public Spittle(Spitter spitter, String message) {
        this(null, spitter, message, new Date());
    }

}
