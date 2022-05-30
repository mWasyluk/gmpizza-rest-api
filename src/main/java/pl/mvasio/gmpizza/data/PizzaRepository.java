package pl.mvasio.gmpizza.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.mvasio.gmpizza.domain.pizza.Pizza;

import java.util.UUID;

@Repository
public interface PizzaRepository extends CrudRepository<Pizza, UUID> {
}
