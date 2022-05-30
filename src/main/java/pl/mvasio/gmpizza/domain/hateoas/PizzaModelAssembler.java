package pl.mvasio.gmpizza.domain.hateoas;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import pl.mvasio.gmpizza.controller.PizzaController;
import pl.mvasio.gmpizza.domain.pizza.Pizza;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PizzaModelAssembler implements RepresentationModelAssembler<Pizza, Pizza> {
    @Override
    public Pizza toModel(Pizza entity) {
        entity.setBaseSauce(new IngredientModelAssembler().toModel(entity.getBaseSauce()));
        entity.setPie(new IngredientModelAssembler().toModel(entity.getPie()));
        entity.setSize(new IngredientModelAssembler().toModel(entity.getSize()));
        entity.getIngredients().forEach(ingredient -> new IngredientModelAssembler().toModel(ingredient));
        if (entity.getImageDomain() != null)
            entity.setImageDomain(new ImageModelAssembler().toModel(entity.getImageDomain()));
        entity.add(linkTo(methodOn(PizzaController.class).getPizzaById(entity.getId())).withSelfRel());
        return entity;
    }
}
