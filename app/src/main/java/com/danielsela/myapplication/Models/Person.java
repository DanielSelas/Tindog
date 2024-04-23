package com.danielsela.myapplication.Models;

import java.util.ArrayList;

public class Person {
    private String name = " ";
    private String location= " ";
    private int daysOfAvailability = 0;
    private int price = 0;
    private boolean isFavorite = false;
    private String personPoster = " ";

    public String getPersonPoster() {
        return personPoster;
    }

    public Person setPersonPoster(String personPoster) {
        this.personPoster = personPoster;
        return this;
    }

    public Person setDaysOfAvailability(int daysOfAvailability) {
        this.daysOfAvailability = daysOfAvailability;
        return this;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Person setLocation(String location) {
        this.location = location;
        return this;
    }

    public int getDaysOfAvailability() {
        return daysOfAvailability;
    }

    public int getPrice() {
        return price;
    }

    public Person setPrice(int price) {
        this.price = price;
        return this;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public Person setFavorite(boolean favorite) {
        isFavorite = favorite;
        return this;
    }


}
