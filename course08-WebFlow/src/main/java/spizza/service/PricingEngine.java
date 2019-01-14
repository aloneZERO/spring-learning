package spizza.service;

import spizza.domain.Order;

public interface PricingEngine {

    float calculateOrderTotal(Order order);

}
