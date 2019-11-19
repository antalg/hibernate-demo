package hu.aensys.tutorial.java.hibernate.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Embeddable
public class ImageSize {

    @NotNull
    @Min(1)
    private Integer width;

    @NotNull
    @Min(1)
    private Integer height;

}
