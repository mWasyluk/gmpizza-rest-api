package pl.mvasio.gmpizza.domain.image;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Entity
@Table(name = "ImageDomains")
public class ImageDomain extends RepresentationModel<ImageDomain> {
    @Id
    @GeneratedValue
    private UUID id;
    @NonNull
    private String name;
    @NonNull
    private String mediaType;
    private byte[] bytes;
}
