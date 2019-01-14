package spizza.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Customer implements Serializable {

    private static final long serialVersionUID = -8770381610390839517L;

    private Integer id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;

    public Customer(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
