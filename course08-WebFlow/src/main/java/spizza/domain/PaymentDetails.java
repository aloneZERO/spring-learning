package spizza.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaymentDetails implements Serializable {

    private static final long serialVersionUID = 5208504852685005188L;

    private PaymentType paymentType;
    private String creditCardNumber;

}
