package entities;

import javax.persistence.*;
import java.util.List;


@Table(name = "hobby")
@Entity
public class Hobby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;
    // this is the inverse side of the relationship, the owning side is in the person entity.
    // the mappedBy attribute is used to specify the name of the field in the person entity that is the owning side of the relationship.
    @ManyToMany(mappedBy = "hobbies")
    private List<Person> persons;


    public Hobby() {
    }

    public Hobby(String name, String description) {
        this.name = name;
        this.description = description;
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String toString() {
        return "Hobby{" + "name=" + name + ", description=" + description + '}';
    }

    public List<Person> getPersons() {
        return persons;
    }


    public void addPerson(Person person) {
        this.persons.add(person);
    }

    public void removePerson(Person person) {
        this.persons.remove(person);
    }
}