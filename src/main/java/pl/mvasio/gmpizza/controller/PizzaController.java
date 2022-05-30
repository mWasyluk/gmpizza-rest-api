package pl.mvasio.gmpizza.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.mvasio.gmpizza.data.PizzaRepository;
import pl.mvasio.gmpizza.domain.hateoas.LinkService;
import pl.mvasio.gmpizza.domain.hateoas.PizzaModelAssembler;
import pl.mvasio.gmpizza.domain.pizza.Pizza;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/pizzas",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public ResponseEntity<?> getAllRegularPizzas(){
        CollectionModel<Pizza> collectionModel = new PizzaModelAssembler().toCollectionModel(pizzaRepository.findAll());
        collectionModel.add(LinkService.ALL_REGULAR_PIZZAS);
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPizzaById(@PathVariable UUID id){
        Optional<Pizza> optionalRegularPizza = pizzaRepository.findById(id);
        if (optionalRegularPizza.isPresent()) {
            RepresentationModel<Pizza> pizzaRepresentationModel = new PizzaModelAssembler().toModel(optionalRegularPizza.get());
            pizzaRepresentationModel.add(LinkService.ALL_REGULAR_PIZZAS);
            return new ResponseEntity<>(pizzaRepresentationModel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping (consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> postPizza(@RequestBody Pizza pizza) {
        Pizza entity = new Pizza(pizza.getName(), pizza.getDescription(), pizza.getPrice(), pizza.getSize(),
                pizza.getPie(), pizza.getBaseSauce(), pizza.getIngredients());
        RepresentationModel<Pizza> pizzaRepresentationModel;
        pizzaRepresentationModel = new PizzaModelAssembler().toModel(pizzaRepository.save(entity));
        pizzaRepresentationModel.add(LinkService.ALL_REGULAR_PIZZAS);

        return new ResponseEntity<>(pizzaRepresentationModel, HttpStatus.CREATED);
    }
}
