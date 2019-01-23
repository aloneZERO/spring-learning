package spittr.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SpittleNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1779429261015441072L;

    private long spittleId;
}
