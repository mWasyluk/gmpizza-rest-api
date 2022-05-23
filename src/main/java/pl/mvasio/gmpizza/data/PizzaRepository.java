package pl.mvasio.gmpizza.data;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.mvasio.gmpizza.domain.Pizza;

@Repository
public interface PizzaRepository extends MongoRepository<Pizza, String> {
    Iterable<Pizza> findAllPizzasByUserIdOrderByCreateDateDesc(String id);
}
