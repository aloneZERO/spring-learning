package spizza.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import spizza.domain.Order;
import spizza.domain.Pizza;
import spizza.domain.PizzaSize;
import spizza.service.PricingEngine;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class PricingEngineImpl implements PricingEngine {

    private static Map<PizzaSize, Float> SIZE_PRICES;

    private static float PRICE_PER_TOPPING = 0.20f;

    static {
        SIZE_PRICES = new HashMap<PizzaSize, Float>();
        SIZE_PRICES.put(PizzaSize.SMALL, 6.99f);
        SIZE_PRICES.put(PizzaSize.MEDIUM, 7.99f);
        SIZE_PRICES.put(PizzaSize.LARGE, 8.99f);
        SIZE_PRICES.put(PizzaSize.GINORMOUS, 9.99f);
    }


    public float calculateOrderTotal(Order order) {
        log.info("Calculating order total");

        float total = 0.0f;
        for (Pizza pizza : order.getPizzas()) {
            float pizzaPrice = SIZE_PRICES.get(pizza.getSize());
            if (pizza.getToppings().size() > 2) {
                pizzaPrice += (pizza.getToppings().size() *
                        PRICE_PER_TOPPING);
            }
            total += pizzaPrice;
        }
        return total;
    }
}
