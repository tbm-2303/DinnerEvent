package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Table(name = "person")
@Entity
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private int age;

    // jpa notation for many to many relationship between person and hobby entities.
    // the join table is used to create a new table in the database that will hold the relationship between the two entities.
    // the join columns are used to specify the column name in the join table that will hold the primary key of the person entity.
    // the inverse join columns are used to specify the column name in the join table that will hold the primary key of the hobby entity.
    // the join table will have two columns, one for the person id and one for the hobby id.
    // this is the owning side of the relationship, the inverse side is in the hobby entity.
    // the mappedBy attribute is used to specify the name of the field in the hobby entity that is the inverse side of the relationship.
    // the mappedBy attribute is used to break the relationship between the two entities.
    // the mappedBy attribute is used to avoid infinite recursion.
    @ManyToMany
    @JoinTable(name = "person_hobbies",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "hobbies_id"))
    private List<Hobby> hobbies = new ArrayList<>();


    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;

    }

    // use this method for adding hobbies to a person in the facade class.
    //this will insure that the person is added to the hobby as well.
    public void addHobby(Hobby hobby) {
        this.hobbies.add(hobby);
        hobby.addPerson(this);
    }
    public void removeHobby(Hobby hobby) {
        if (this.hobbies.remove(hobby)) {
            hobby.removePerson(this);
        }
    }


    public List<Hobby> getHobbies() {
        return hobbies;
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

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", age=" + age + '}';
    }


}