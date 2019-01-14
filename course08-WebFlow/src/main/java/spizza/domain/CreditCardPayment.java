package spizza.domain;


import lombok.Setter;

@Setter
public class CreditCardPayment extends Payment {

    private String authorization;

    @Override
    public String toString() {
        return "CREDIT:  $" + getAmount() + " ; AUTH: " + authorization;
    }
}
