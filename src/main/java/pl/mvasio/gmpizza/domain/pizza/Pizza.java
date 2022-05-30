package pl.mvasio.gmpizza.domain.pizza;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
import pl.mvasio.gmpizza.domain.image.ImageDomain;
import pl.mvasio.gmpizza.domain.ingredient.Ingredient;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "Pizzas")
public class Pizza extends RepresentationModel<Pizza> {
     @Id
     @GeneratedValue
     private UUID id;
     private String name;
     private String description;
     private double price;
     private int maxIngredientsAmount = 12;
     @ManyToOne
     private Ingredient size;
     @ManyToOne
     private Ingredient pie;
     @ManyToOne
     private Ingredient baseSauce;
     @ManyToMany(targetEntity = Ingredient.class)
     private Collection<Ingredient> ingredients;
     @ManyToMany(targetEntity = PizzaCategory.class, cascade = CascadeType.ALL)
     private Collection<PizzaCategory> categories;
     @ManyToOne(targetEntity = ImageDomain.class, cascade = CascadeType.ALL)
     private ImageDomain imageDomain;

     public Pizza(@NonNull String name, @NonNull String description, double price,
                  @NonNull Ingredient size, @NonNull Ingredient pie, @NonNull Ingredient baseSauce,
                  @NonNull Collection<Ingredient> ingredients, int maxIngredientsAmount,
                  Collection<PizzaCategory> categories, ImageDomain imageDomain) {
          this(name, description, price, size, pie, baseSauce, ingredients);

          this.maxIngredientsAmount = maxIngredientsAmount;
          this.categories = categories;
          this.imageDomain = imageDomain;
     }
     
     public Pizza(@NonNull String name, @NonNull String description, double price,
                  @NonNull Ingredient size, @NonNull Ingredient pie, @NonNull Ingredient baseSauce,
                  @NonNull Collection<Ingredient> ingredients){
          this.name = name;
          this.description = description;
          this.price = price;

          this.size = size;
          this.pie = pie;
          this.baseSauce = baseSauce;

          this.ingredients = ingredients;
     }

     @Entity
     @RequiredArgsConstructor
     @NoArgsConstructor
     @Table(name = "PizzaCategories")
     public static class PizzaCategory {
          public static final PizzaCategory VEG = new PizzaCategory("Wegetariańskie");
          public static final PizzaCategory HOT = new PizzaCategory("Ostre");
          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private long id;
          @NonNull
          private String name;
     }
}
