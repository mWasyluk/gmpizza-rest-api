package pl.mvasio.gmpizza.domain.hateoas;

import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import pl.mvasio.gmpizza.controller.ImageController;
import pl.mvasio.gmpizza.domain.image.ImageDomain;

import java.io.IOException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ImageModelAssembler implements RepresentationModelAssembler<ImageDomain, ImageDomain> {
    @Override
    public ImageDomain toModel(ImageDomain entity) {
        Links links = entity.getLinks();
        if (!links.hasLink(LinkRelation.of("self"))) {
            try {
                entity.add(linkTo(methodOn(ImageController.class).getImage(entity.getName())).withSelfRel());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!links.hasLink(LinkRelation.of("allImages")))
            entity.add(linkTo(ImageController.class).withRel("allImages"));
        return entity;
    }
}
