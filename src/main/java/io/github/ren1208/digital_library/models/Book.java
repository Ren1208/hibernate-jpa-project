package io.github.ren1208.digital_library.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

/**
 * @author Artyom Semenchenko
 */

@Data
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "person_id")
    private Person owner;

    @NotEmpty(message = "Название книги не может быть пустым.")
    @Size(min = 2, max = 200, message = "Название книги должно содержать от 2 до 100 символов.")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "ФИО автора не может быть пустым.")
    @Size(min = 4, max = 200, message = "ФИО автора должно содержать от 4 до 200 символов.")
    @Column(name = "author")
    private String author;

    @Max(value = 2024, message = "Год выпуска книги не может быть позднее 2024 года.")
    @Column(name = "year_of_release")
    private int yearOfRelease;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean expired;

}
