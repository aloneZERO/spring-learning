package spittr.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DuplicateSpittleException extends RuntimeException {
    public DuplicateSpittleException(String message) {
        super(message);
    }
}
