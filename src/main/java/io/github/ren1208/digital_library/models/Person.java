package io.github.ren1208.digital_library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * @author Artyom Semenchenko
 */

@Data
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Book> books;

    @NotEmpty(message = "ФИО не может быть пустым.")
    @Size(min = 4, max = 200, message = "ФИО должно содержать от 4 до 200 символов.")
    @Column(name = "name")
    private String name;

    @Min(value = 1900, message = "Год рождения должнен быть больше 1900.")
    @Max(value = 2024, message = "Год рождения должен быть меньше 2025.")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "password")
    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;

    @Column(name = "role")
    private String role;

}
