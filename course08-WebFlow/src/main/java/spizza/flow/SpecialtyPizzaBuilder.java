package spizza.flow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import spizza.domain.Pizza;
import spizza.domain.Topping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SpecialtyPizzaBuilder implements Action {

    public Event execute(RequestContext request) throws Exception {
        String type = request.getRequestParameters().get("pizzaType");

        log.debug("BUILDING A SPECIALTY PIZZA:  " + type);

        Pizza pizza = (Pizza) request.getFlowScope().get("pizza");
        if ("MEAT".equals(type)) {
            log.debug("BUILDING A CARNIVORE");

            List<Topping> meats = new ArrayList<>();

            meats.add(Topping.CANADIAN_BACON);
            meats.add(Topping.HAMBURGER);
            meats.add(Topping.PEPPERONI);
            meats.add(Topping.SAUSAGE);

            pizza.setToppings(meats);
        } else if ("VEGGIE".equals(type)) {
            log.debug("BUILDING A HERBIVORE");
            List<Topping> meats = new ArrayList<Topping>();

            meats.add(Topping.GREEN_PEPPER);
            meats.add(Topping.MUSHROOM);
            meats.add(Topping.PINEAPPLE);
            meats.add(Topping.TOMATO);

            pizza.setToppings(meats);
        } else if ("THEWORKS".equals(type)) {
            log.debug("BUILDING AN OMNIVORE");

            List<Topping> meats = new ArrayList<Topping>();
            System.out.println("THE WORKS!");

            meats.add(Topping.CANADIAN_BACON);
            meats.add(Topping.HAMBURGER);
            meats.add(Topping.PEPPERONI);
            meats.add(Topping.SAUSAGE);
            meats.add(Topping.GREEN_PEPPER);
            meats.add(Topping.MUSHROOM);
            meats.add(Topping.PINEAPPLE);
            meats.add(Topping.TOMATO);
            meats.add(Topping.EXTRA_CHEESE);
            meats.add(Topping.ONION);
            meats.add(Topping.JALAPENO);

            pizza.setToppings(meats);
        }

        request.getFlowScope().put("pizza", pizza);

        return new Event(this, "success");
    }
}
