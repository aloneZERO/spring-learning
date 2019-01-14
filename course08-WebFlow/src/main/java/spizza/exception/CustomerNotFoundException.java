package spizza.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomerNotFoundException extends Exception {

    private static final long serialVersionUID = 115217274937684049L;

    public CustomerNotFoundException(String message) {
        super(message);
    }
}
