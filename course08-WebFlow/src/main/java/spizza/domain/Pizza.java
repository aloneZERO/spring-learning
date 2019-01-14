package spizza.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Pizza implements Serializable {
    private static final long serialVersionUID = -4650052405410777627L;
    private PizzaSize size;
    private List<Topping> toppings;

    public Pizza() {
        toppings = new ArrayList<Topping>();
        size = PizzaSize.LARGE;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public void setToppings(String[] toppingStrings) {
        for (String toppingStr : toppingStrings) {
            toppings.add(Topping.valueOf(toppingStr));
        }
    }
}
