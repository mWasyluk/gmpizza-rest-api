package pl.mvasio.gmpizza.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@Document(value = "Pizzas")
public class Pizza {
    @Id
    private String id = UUID.randomUUID().toString();;
    @NonNull
    private String userId;
    @Size(min = 3, message = "Nazwa jest za krótka - musi zawierać min. 3 znaki.")
    @Size(max = 64, message = "Nazwa jest za długa - może zawierać maks. 64 znaki.")
    @NonNull
    private String name;
    private LocalDateTime createDate;
    @NonNull
    private double price;
    @NonNull
    private Ingredient size;
    @NonNull
    private Ingredient pie;
    @NonNull
    private Ingredient baseSauce;
    @NonNull
    private List<Ingredient> ingredients;
    private String note;
}
