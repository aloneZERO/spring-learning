package spizza.domain;


public class CashOrCheckPayment extends Payment {

    @Override
    public String toString() {
        return "CASH or CHECK:  $" + getAmount();
    }

}
