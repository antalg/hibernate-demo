package hu.aensys.tutorial.java.hibernate.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "video_file")
public class VideoFile extends File {

    @Column(name = "length_ms")
    @NotNull
    private Long lengthInMilliseconds;

    @Enumerated(EnumType.STRING)
    @NotNull
    private VideoFileFormat format;

}
