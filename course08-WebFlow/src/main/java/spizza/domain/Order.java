package spizza.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author justZero
 * @since 2019/1/6
 */
@Getter
@Setter
public class Order implements Serializable {

    private static final long serialVersionUID = -5817236244625200770L;

    private Customer customer;
    private List<Pizza> pizzas;
    private Payment payment;

    public Order() {
        pizzas = new ArrayList<>();
        customer = new Customer();
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public float getTotal() {
        return 0.0f;
    }
}
