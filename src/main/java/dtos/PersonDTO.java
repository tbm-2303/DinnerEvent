package dtos;

import entities.Person;

import java.util.List;
import java.util.stream.Collectors;

public class PersonDTO {
    private Long id;
    private String name;
    private int age;
    private List<HobbyDTO> hobbies;


    public PersonDTO(Person person) {
        if (person.getId() != null) {
            this.id = person.getId();
        }
        this.name = person.getName();
        this.age = person.getAge();
        if (person.getHobbies() != null) {
            this.hobbies = person.getHobbies().stream().map(h -> new HobbyDTO(h)).collect(Collectors.toList());
        }
    }

    public static List<PersonDTO> getDtos(List<Person> persons) {
        return persons.stream().map(p -> new PersonDTO(p)).collect(Collectors.toList());
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<HobbyDTO> getHobbies() {
        return hobbies;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}