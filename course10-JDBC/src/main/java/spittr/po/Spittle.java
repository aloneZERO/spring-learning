package spittr.po;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Spittle implements Serializable {

    private static final long serialVersionUID = 3851421237037355228L;

    private final Long id;
    private final Spitter spitter;
    private final String message;
    private final Date postedTime;

}
