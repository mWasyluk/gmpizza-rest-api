package pl.mvasio.gmpizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mvasio.gmpizza.data.IngredientRepository;
import pl.mvasio.gmpizza.domain.hateoas.IngredientModelAssembler;
import pl.mvasio.gmpizza.domain.hateoas.LinkService;
import pl.mvasio.gmpizza.domain.ingredient.Ingredient;

import java.util.Optional;

@RestController
@RequestMapping(value = "/ingredients", produces = MediaType.APPLICATION_JSON_VALUE)
public class IngredientController {
    @Autowired
    private IngredientRepository ingredientRepository;

    @GetMapping
    public ResponseEntity<?> getAllIngredients( @RequestParam(name = "type", required = false) String type ){
        if (type != null){
            Iterable<Ingredient> allIngredientsByType = ingredientRepository.findAllIngredientsByType(Ingredient.Type.valueOf(type.toUpperCase()));
            CollectionModel<Ingredient> collectionModel = new IngredientModelAssembler().toCollectionModel(allIngredientsByType);
            collectionModel.add(LinkService.ALL_INGREDIENTS);
            return new ResponseEntity<>( collectionModel, HttpStatus.OK );
        }
        Iterable<Ingredient> allIngredients = ingredientRepository.findAll();
        CollectionModel<Ingredient> collectionModel = new IngredientModelAssembler().toCollectionModel(allIngredients);
        collectionModel.add(LinkService.ALL_INGREDIENTS);

        return new ResponseEntity<>( collectionModel, HttpStatus.OK );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable("id") long id){
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(id);
        if (optionalIngredient.isPresent()){
            RepresentationModel<Ingredient> representationModel = new IngredientModelAssembler().toModel(optionalIngredient.get());
            representationModel.add(LinkService.ALL_INGREDIENTS);
            return new ResponseEntity<>(representationModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postIngredient(@RequestBody Ingredient ingredient) {
        Optional<Ingredient> optionalById = ingredientRepository.findById(ingredient.getId());
        if (!optionalById.isPresent()){
            Ingredient save = ingredientRepository.save(ingredient);
            RepresentationModel<Ingredient> representationModel = new IngredientModelAssembler().toModel(save);
            representationModel.add(LinkService.ALL_INGREDIENTS);
            return new ResponseEntity<>(representationModel, HttpStatus.CREATED);
        }
        RepresentationModel<Ingredient> representationModel = new IngredientModelAssembler().toModel(optionalById.get());
        representationModel.add(LinkService.ALL_INGREDIENTS);
        return new ResponseEntity<>(representationModel, HttpStatus.CONFLICT);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> putIngredient(@PathVariable long id, @RequestBody Ingredient ingredient){
        Optional<Ingredient> optionalById = ingredientRepository.findById(id);
        ingredient.setId(id);
        if (optionalById.isPresent()){
            Ingredient save = ingredientRepository.save(ingredient);
            RepresentationModel<Ingredient> representationModel = new IngredientModelAssembler().toModel(save);
            representationModel.add(LinkService.ALL_INGREDIENTS);
            return new ResponseEntity<>(representationModel, HttpStatus.CREATED);
        }
        RepresentationModel<Ingredient> representationModel = new IngredientModelAssembler().toModel(ingredient);
        representationModel.add(LinkService.ALL_INGREDIENTS);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
