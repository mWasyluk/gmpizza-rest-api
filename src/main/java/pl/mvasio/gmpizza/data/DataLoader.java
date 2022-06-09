package pl.mvasio.gmpizza.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import pl.mvasio.gmpizza.domain.image.ImageDomain;
import pl.mvasio.gmpizza.domain.ingredient.Ingredient;
import pl.mvasio.gmpizza.domain.pizza.Pizza;
import pl.mvasio.gmpizza.security.User;

import java.util.Arrays;
import java.util.Collections;

@Service
@Profile("dev-pl")
@Slf4j
public class DataLoader implements ApplicationRunner {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public void run(ApplicationArguments args) {
        ingredientRepository.deleteAll();
        pizzaRepository.deleteAll();
        userRepository.deleteAll();

        ingredientRepository.saveAll(Arrays.asList(
                new Ingredient("Szynka", 4.50, Ingredient.Type.MEAT),
                new Ingredient("Kiełbasa", 4.50, Ingredient.Type.MEAT),
                new Ingredient("Pieczony kurczak", 5.00, Ingredient.Type.MEAT),

                new Ingredient("Rukola", 2.50, Ingredient.Type.VEGGIE),
                new Ingredient("Pieczarki", 3.00, Ingredient.Type.VEGGIE),
                new Ingredient("Kukurydza", 3.50, Ingredient.Type.VEGGIE),

                new Ingredient("Ananas", 4.70, Ingredient.Type.UNUSUAL),

                new Ingredient("Pomidorowy", 2.00, Ingredient.Type.BASE_SAUCE),

                new Ingredient("Cienka Finetta", 0.00, Ingredient.Type.PIE),
                new Ingredient("Serowe brzegi", 2.00, Ingredient.Type.PIE),

                new Ingredient("Mała - 24 cm", 2.00, Ingredient.Type.SIZE),
                new Ingredient("Duża - 34 cm", 2.00, Ingredient.Type.SIZE),

                new Ingredient("Ser mozzarella", 2.00, Ingredient.Type.CHEESE)



        ));
        Pizza test = new Pizza(
                "test",
                "Szynka, ananas, ser mozarella i sos pomidorowy",
                27.99,
                ingredientRepository.findIngredientByName("Mała - 24 cm").get(),
                ingredientRepository.findIngredientByName("Cienka Finetta").get(),
                ingredientRepository.findIngredientByName("Pomidorowy").get(),
                Collections.emptyList());


        Pizza klasyczna = new Pizza(
                "Klasyczna",
                "Szynka, pieczarki, ser mozarella i sos pomidorowy",
                27.99,
                ingredientRepository.findIngredientByName("Mała - 24 cm").get(),
                ingredientRepository.findIngredientByName("Cienka Finetta").get(),
                ingredientRepository.findIngredientByName("Pomidorowy").get(),
                Arrays.asList(
                        ingredientRepository.findIngredientByName("Szynka").get(),
                        ingredientRepository.findIngredientByName("Pieczarki").get(),
                        ingredientRepository.findIngredientByName("Ser mozzarella").get()),
                12,
                Collections.singleton(Pizza.PizzaCategory.VEG),
                new ImageDomain("pizza-ham.jpeg",
                        MediaType.IMAGE_JPEG_VALUE));

        Pizza oKurcze = new Pizza(
                "O Kurcze!",
                "Pieczony kurczak, kukurydza, ser mozarella i sos pomidorowy",
                27.99,
                ingredientRepository.findIngredientByName("Mała - 24 cm").get(),
                ingredientRepository.findIngredientByName("Cienka Finetta").get(),
                ingredientRepository.findIngredientByName("Pomidorowy").get(),
                Arrays.asList(
                        ingredientRepository.findIngredientByName("Pieczony kurczak").get(),
                        ingredientRepository.findIngredientByName("Kukurydza").get(),
                        ingredientRepository.findIngredientByName("Ser mozzarella").get()),
                12,
                Collections.singleton(null),
                new ImageDomain("pizza-chicken-pepper-corn.jpeg",
                        MediaType.IMAGE_JPEG_VALUE));

        Pizza hawajska = new Pizza(
                "Hawajska",
                "Szynka, ananas, ser mozarella i sos pomidorowy",
                27.99,
                ingredientRepository.findIngredientByName("Mała - 24 cm").get(),
                ingredientRepository.findIngredientByName("Cienka Finetta").get(),
                ingredientRepository.findIngredientByName("Pomidorowy").get(),
                Arrays.asList(
                        ingredientRepository.findIngredientByName("Szynka").get(),
                        ingredientRepository.findIngredientByName("Ananas").get(),
                        ingredientRepository.findIngredientByName("Ser mozzarella").get()),
                12,
                Collections.singleton(null),
                new ImageDomain("pizza-hawaiian.jpeg",
                        MediaType.IMAGE_JPEG_VALUE));

        pizzaRepository.saveAll(Arrays.asList(test, klasyczna, oKurcze, hawajska));
        log.info("Pizzas data uploaded.");

        userRepository.save(new User("user", "pass"));
        log.info("User data uploaded.");
    }
}
