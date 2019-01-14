package spizza.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public abstract class Payment implements Serializable {

    private static final long serialVersionUID = -1192398242439506396L;

    private float amount;
}
