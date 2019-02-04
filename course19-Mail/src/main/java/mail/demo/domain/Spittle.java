package mail.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Spittle {

    private final Long id;
    private Spitter spitter;
    private final String text;
    private final Date postedTime;

}