package hu.aensys.tutorial.java.hibernate.entity;

import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "image_file")
public class ImageFile extends File {

    @Embedded
    @NotNull
    private ImageSize size;

}
