package hu.aensys.tutorial.java.hibernate.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(of = {"username"})
@Entity
@Table(
        name = "application_user",
        uniqueConstraints = @UniqueConstraint(name = "unique_user_username", columnNames = {"username"})
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotEmpty
    private String username;

    @Column(name = "full_name")
    @NotEmpty
    private String fullName;

}
