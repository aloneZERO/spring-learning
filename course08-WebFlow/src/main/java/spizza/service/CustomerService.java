package spizza.service;

import spizza.domain.Customer;
import spizza.exception.CustomerNotFoundException;

public interface CustomerService {
    Customer lookupCustomer(String phoneNumber) throws CustomerNotFoundException;
}