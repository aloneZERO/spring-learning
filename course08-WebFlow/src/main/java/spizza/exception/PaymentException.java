package spizza.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PaymentException extends Exception {

    private static final long serialVersionUID = -2436754665020593941L;

    public PaymentException(String message) {
        super(message);
    }
}
