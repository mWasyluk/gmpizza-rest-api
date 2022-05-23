package pl.mvasio.gmpizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.mvasio.gmpizza.data.IngredientRepository;
import pl.mvasio.gmpizza.domain.Ingredient;

import java.util.List;

@RestController
@RequestMapping(value = "/ingredients", produces = "application/json")
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public List<Ingredient> getIngredients( @RequestParam(name = "type", required = false) String type ){
        if (type != null){
            return ingredientRepository.findAllIngredientsByType(type.toUpperCase());
        }
        return ingredientRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable("id") String id){
        return ingredientRepository.findById(id)
                .map(ingredient -> new ResponseEntity<>(ingredient, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Ingredient> postIngredient(@Validated @RequestBody Ingredient ingredient) {
        if (ingredientRepository.findIngredientByName(ingredient.getName()) == null){
            ingredientRepository.save(ingredient);
            return new ResponseEntity<>(ingredient, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(ingredientRepository.findIngredientByName(ingredient.getName()), HttpStatus.CONFLICT);
    }
}
