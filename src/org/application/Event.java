package org.application;

public class Event {
    private int id; // Add ID for event
    private String name;
    private String date;
    private String location;
    private double price;

    public Event(int id, String name, String date, String location, double price) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.location = location;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + " on " + date + " at " + location + " with price " + price;
    }
}
