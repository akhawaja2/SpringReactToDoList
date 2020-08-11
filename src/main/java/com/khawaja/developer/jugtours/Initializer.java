/* Initializer will load default data*/

package com.khawaja.developer.jugtours;

//Importing the Group, Event, GroupRepo from our model



//Spring boot stuff
import com.khawaja.developer.jugtours.model.ListItem;
import com.khawaja.developer.jugtours.model.ListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

//Spring will auto-detect these classes for dependency injection
@Component
class Initializer implements CommandLineRunner {

    private final ListRepository repository;

    public Initializer(ListRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name ->
                repository.save(new ListItem(name)));

        repository.findAll().forEach(System.out::println);
    }
}