package pl.mvasio.gmpizza.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mvasio.gmpizza.domain.Ingredient;

import java.util.List;

@Repository
public interface IngredientRepository extends MongoRepository<Ingredient, String> {
    List<Ingredient> findAllIngredientsByType(String type);
    Ingredient findIngredientByName(String name);
}
