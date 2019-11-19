package hu.aensys.tutorial.java.hibernate.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@EqualsAndHashCode(of = {"name", "owner"})
@Entity
@Table(
        name = "file",
        uniqueConstraints = @UniqueConstraint(name = "unique_file_name_owner", columnNames = {"name", "owner_id"})
)
@Inheritance(strategy = InheritanceType.JOINED)
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty
    private String name;

    @Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    @NotNull
    private User owner;

}
