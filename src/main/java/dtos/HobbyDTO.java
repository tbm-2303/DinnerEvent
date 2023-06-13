package dtos;

import entities.Hobby;
import entities.Person;

import java.util.List;
import java.util.stream.Collectors;

public class HobbyDTO {
    private Long id;
    private String name;
    private String description;
    //break the relationship between hobby and person by only showing the name of the person
    //this is how we can avoid infinite recursion, by breaking the relationship between the two entities.
    private List<String> persons;

    public HobbyDTO(Hobby h) {
        if (h.getId() != null) {
            this.id = h.getId();
        }
        this.name = h.getName();
        this.description = h.getDescription();
        if (h.getPersons() != null) {
            this.persons = h.getPersons().stream().map(Person::getName).collect(Collectors.toList());
        }

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPersons() {
        return persons;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
