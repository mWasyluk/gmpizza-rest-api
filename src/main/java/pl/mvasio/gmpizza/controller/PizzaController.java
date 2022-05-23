package pl.mvasio.gmpizza.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.mvasio.gmpizza.data.PizzaRepository;
import pl.mvasio.gmpizza.domain.Pizza;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/pizzas", produces = "application/json")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public ResponseEntity<?> getAllPizzas(){
        return new ResponseEntity<>(pizzaRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPizzaById(@PathVariable String id){
        Optional<Pizza> optional = pizzaRepository.findById(id);
        return optional.map(pizza -> new ResponseEntity<>(pizza, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> postPizza(@Validated @RequestBody Pizza pizza, Errors errors){
        if (errors.hasErrors()){
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        else {
            pizzaRepository.save(pizza);
            return new ResponseEntity<>(pizza, HttpStatus.CREATED);
        }
    }
}
