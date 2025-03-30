package ru.semenchenko.library.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.semenchenko.library.models.Person;
import ru.semenchenko.library.services.PeopleService;

/**
 * @author Artyom Semenchenko
 */

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        // Проверяем, есть ли человек с таким ФИО в БД
        peopleService.getPersonByName(person.getName()).ifPresent(foundPerson -> {
            // Если найденный человек - не тот, которого мы редактируем (разные ID)
            if (person.getPersonId() != foundPerson.getPersonId()) {
                errors.rejectValue("name", "", "Человек с таким ФИО уже существует");
            }
        });
    }
}
