package spizza.service;


import spizza.exception.PaymentException;


public class PaymentProcessor {

   public void approveCreditCard(String creditCardNumber, String expMonth,
                     String expYear, float amount) throws PaymentException {
      if (amount > 20.00) {
         throw new PaymentException();
      }
   }
}
