package spizza.service.impl;


import org.springframework.stereotype.Service;
import spizza.domain.Customer;
import spizza.exception.CustomerNotFoundException;
import spizza.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

    public Customer lookupCustomer(String phoneNumber) throws CustomerNotFoundException {
        if ("12345678".equals(phoneNumber)) {
            Customer customer = new Customer();
            customer.setId(123);
            customer.setName("Craig Walls");
            customer.setAddress("3700 Dunlavy Rd");
            customer.setCity("Denton");
            customer.setState("TX");
            customer.setZipCode("76210");
            customer.setPhoneNumber(phoneNumber);
            return customer;
        } else {
            throw new CustomerNotFoundException();
        }
    }
}
