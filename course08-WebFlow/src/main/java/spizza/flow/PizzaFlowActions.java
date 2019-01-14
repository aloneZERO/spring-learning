package spizza.flow;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import spizza.domain.*;
import spizza.exception.CustomerNotFoundException;
import spizza.service.CustomerService;

import static spizza.domain.PaymentType.CREDIT_CARD;

@Slf4j
@Component
@AllArgsConstructor
public class PizzaFlowActions {

    CustomerService customerService;

    public Customer lookupCustomer(String phoneNumber)
            throws CustomerNotFoundException {
        Customer customer = customerService.lookupCustomer(phoneNumber);
        if (customer != null) {
            return customer;
        } else {
            throw new CustomerNotFoundException();
        }
    }

    public void addCustomer(Customer customer) {
        log.warn("TODO: Flesh out the addCustomer() method.");
    }

    public Payment verifyPayment(PaymentDetails paymentDetails) {
        Payment payment = null;
        if (paymentDetails.getPaymentType() == CREDIT_CARD) {
            payment = new CreditCardPayment();
        } else {
            payment = new CashOrCheckPayment();
        }
        return payment;
    }

    public void saveOrder(Order order) {
        log.warn("TODO: Flesh out the saveOrder() method.");
    }

    public boolean checkDeliveryArea(String zipCode) {
        log.warn("TODO: Flesh out the checkDeliveryArea() method.");
        return "75075".equals(zipCode);
    }
}
