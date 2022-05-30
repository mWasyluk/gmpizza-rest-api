package pl.mvasio.gmpizza.domain.ingredient;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "Ingredients")
public class Ingredient extends RepresentationModel<Ingredient>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private Type type;

    public Ingredient(@NonNull String name, double price, @NonNull Type type) {
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public enum Type{
        SIZE, BASE_SAUCE, PIE, VEGGIE, MEAT, SAUCE, CHEESE, UNUSUAL
    }
}
