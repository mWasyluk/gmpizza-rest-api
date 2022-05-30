package pl.mvasio.gmpizza.domain.hateoas;

import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import pl.mvasio.gmpizza.controller.IngredientController;
import pl.mvasio.gmpizza.domain.ingredient.Ingredient;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class IngredientModelAssembler implements RepresentationModelAssembler<Ingredient, Ingredient> {
    @Override
    public Ingredient toModel(Ingredient entity) {
        Links links = entity.getLinks();
        if (!links.hasLink(LinkRelation.of("self")))
            entity.add(linkTo(methodOn(IngredientController.class).getIngredientById(entity.getId())).withSelfRel());
        return entity;
    }
}
