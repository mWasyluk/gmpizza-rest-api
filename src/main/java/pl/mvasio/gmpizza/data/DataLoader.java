package pl.mvasio.gmpizza.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.mvasio.gmpizza.domain.Ingredient;
import pl.mvasio.gmpizza.domain.Pizza;
import pl.mvasio.gmpizza.security.User;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private IngredientRepository ingredientRepository;
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ingredientRepository.deleteAll();
        pizzaRepository.deleteAll();
        userRepository.deleteAll();

        ingredientRepository.saveAll(Arrays.asList(
                new Ingredient("Szynka", 4.50, Ingredient.Type.MEAT),
                new Ingredient("Kiełbasa", 4.50, Ingredient.Type.MEAT),
                new Ingredient("Rukola", 2.50, Ingredient.Type.VEGGIE),
                new Ingredient("Pomidorowy", 2.00, Ingredient.Type.BASE_SAUCE),
                new Ingredient("Ananas", 4.70, Ingredient.Type.UNUSUAL),
                new Ingredient("Serowe brzegi", 2.00, Ingredient.Type.PIE),
                new Ingredient("Duża - 34 cm", 2.00, Ingredient.Type.SIZE)
        ));

        Pizza samplePizza = new Pizza(
                "test-user-id", "Test-name", 24.55,
                ingredientRepository.findIngredientByName("Duża - 34 cm"),
                ingredientRepository.findIngredientByName("Serowe brzegi"),
                ingredientRepository.findIngredientByName("Pomidorowy"),
                Arrays.asList(
                        ingredientRepository.findIngredientByName("Szynka"),
                        ingredientRepository.findIngredientByName("Ananas")));

        samplePizza.setNote("To jest przykładowa nota dołączona do pizzy.");
        samplePizza.setCreateDate(LocalDateTime.now());

        pizzaRepository.save(samplePizza);

        userRepository.save(new User("user", "pass"));
    }
}
