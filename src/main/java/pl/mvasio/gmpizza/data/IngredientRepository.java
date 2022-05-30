package pl.mvasio.gmpizza.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mvasio.gmpizza.domain.ingredient.Ingredient;

import java.util.Optional;

@Repository
public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
    Iterable<Ingredient> findAllIngredientsByType(Ingredient.Type type);
    Optional<Ingredient> findIngredientByName(String name);
}

