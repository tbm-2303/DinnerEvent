package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "event")
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time")
    private String time;

    @Column(name = "location")
    private String location;

    @Column(name = "dish")
    private String dish;

    @Column(name = "price")
    private int price;

    @OneToMany(mappedBy="event")
    private List<Assignment> assignments = new ArrayList<>();

    public Event() {
    }

    public Event(String time, String location, String dish, int price) {
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.price = price;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getDish() {
        return dish;
    }
    public void setDish(String dish) {
        this.dish = dish;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public List<Assignment> getAssignments() {
        return assignments;
    }
    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }
}