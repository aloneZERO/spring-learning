package spizza.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spizza.domain.Order;
import spizza.service.OrderService;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    public void saveOrder(Order order) {
        log.debug("SAVING ORDER:  ");
        log.debug("   Customer:  " + order.getCustomer().getName());
        log.debug("   # of Pizzas:  " + order.getPizzas().size());
        log.debug("   Payment:  " + order.getPayment());
    }
}
