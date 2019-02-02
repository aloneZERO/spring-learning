package jms.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Spittle implements Serializable {

    private static final long serialVersionUID = -7429082818083302112L;
    private static final Random random = new Random();

    private Long id;
    private Spitter spitter;
    private String message;
    private Date postedTime;

    public Spittle(String message) {
        this(random.nextLong(), null, message, new Date());
    }
}
