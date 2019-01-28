package spittr.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpittleNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8856796082906525486L;

    private long spittleId;
}
