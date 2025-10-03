package com.example.firstproject.api;

import com.example.firstproject.model.Person;
import com.example.firstproject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST Controller for managing Person entities.
 * <p>
 * Provides endpoints to perform CRUD operations on Person objects.
 * Base URL for all endpoints: /api/v1/person
 */
@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    /**
     * Constructor to inject PersonService dependency.
     *
     * @param personService Service layer handling business logic for Person entities.
     */
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    /**
     * Creates a new Person in the system.
     * <p>
     * Endpoint: POST /api/v1/person
     *
     * @param person The Person object to be added. Must be non-null and valid.
     */
    @PostMapping
    public void addPerson(@Valid @NonNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    /**
     * Retrieves all Person entities.
     * <p>
     * Endpoint: GET /api/v1/person
     *
     * @return List of all Person objects.
     */
    @GetMapping
    public List<Person> getAllPerson() {
        return personService.getAllPersons();
    }

    /**
     * Retrieves a Person by their unique identifier.
     * <p>
     * Endpoint: GET /api/v1/person/{id}
     *
     * @param id UUID of the Person to retrieve.
     * @return Optional containing the Person if found, or empty if not found.
     */
    @GetMapping(path = "{id}")
    public Optional<Person> getPersonById(@PathVariable("id") UUID id) {
        return personService.getPersonById(id);
    }

    /**
     * Deletes a Person by their unique identifier.
     * <p>
     * Endpoint: DELETE /api/v1/person/{id}
     *
     * @param id UUID of the Person to delete.
     * @return int indicating number of records deleted (typically 0 or 1).
     */
    @DeleteMapping(path = "{id}")
    public int deletePerson(@PathVariable("id") UUID id) {
        return personService.deletePerson(id);
    }

    /**
     * Updates an existing Person by their unique identifier.
     * <p>
     * Endpoint: PUT /api/v1/person/{id}
     *
     * @param id            UUID of the Person to update.
     * @param updatedPerson The Person object containing updated details. Must be non-null and valid.
     * @return int indicating number of records updated (typically 0 or 1).
     */
    @PutMapping("{id}")
    public int updatePerson(@PathVariable("id") UUID id,
                            @Valid @NonNull @RequestBody Person updatedPerson) {
        return personService.updatePerson(id, updatedPerson);
    }
}
