package spittr.po;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "spittle")
@Data
@AllArgsConstructor
public class Spittle implements Serializable {

    private static final long serialVersionUID = 3851421237037355228L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spitter")
    private Spitter spitter;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private Date postedTime;

    public Spittle() {
        this.postedTime = new Date();
    }

    public Spittle(Long id) {
        this.id = id;
    }

    public Spittle(Spitter spitter, String message) {
        this(null, spitter, message, new Date());
    }

}
