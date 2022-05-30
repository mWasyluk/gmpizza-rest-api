package pl.mvasio.gmpizza.domain.hateoas;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import pl.mvasio.gmpizza.controller.ImageController;
import pl.mvasio.gmpizza.controller.IngredientController;
import pl.mvasio.gmpizza.controller.PizzaController;

public interface LinkService {
    Link ALL_INGREDIENTS = WebMvcLinkBuilder.linkTo(IngredientController.class).withRel("all_ingredients");
    Link ALL_REGULAR_PIZZAS = WebMvcLinkBuilder.linkTo(PizzaController.class).withRel("all_regular_pizzas");
    Link ALL_IMAGES = WebMvcLinkBuilder.linkTo(ImageController.class).withRel("all_images");
}
