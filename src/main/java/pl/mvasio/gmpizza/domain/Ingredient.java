package pl.mvasio.gmpizza.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@Document(value = "Ingredients")
public class Ingredient {
    @Id
    private String id = UUID.randomUUID().toString();
    @NonNull
    private String name;
    @NonNull
    private double price;
    @NonNull
    private Type type;

    public enum Type{
        SIZE, BASE_SAUCE, PIE, VEGGIE, MEAT, SAUCE, CHEESE, UNUSUAL
    }

    public boolean equals(Object obj){
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Ingredient other = (Ingredient) obj;
        return name.equals(other.name);
    }
}
