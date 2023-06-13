package dtos;

import entities.Event;

public class EventDTO {

    private Long id;
    private String time;
    private String location;
    private String dish;
    private int price;


    public EventDTO(Event event) {
        if (event.getId() != null) {
            this.id = event.getId();
        }
        this.time = event.getTime();
        this.location = event.getLocation();
        this.dish = event.getDish();
        this.price = event.getPrice();
    }


    public EventDTO(String time, String location, String dish, int price) {
        this.time = time;
        this.location = location;
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
}
