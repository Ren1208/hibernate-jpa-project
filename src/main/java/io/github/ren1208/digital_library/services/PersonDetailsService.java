package io.github.ren1208.digital_library.services;

import io.github.ren1208.digital_library.models.Person;
import io.github.ren1208.digital_library.repositories.PeopleRepository;
import io.github.ren1208.digital_library.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Artyom Semenchenko
 */

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = peopleRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        return new PersonDetails(person);
    }
}
