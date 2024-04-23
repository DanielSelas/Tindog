package com.danielsela.myapplication.Models;

import com.danielsela.myapplication.Interfaces.OnChatButtonClickListener;

public class Dog {
    private String name= " ";
    private String location = " ";
    private String playStyle = " ";
    private String bread = " ";
    private float energy = 0.0f;
    private boolean isFavorite = false;
    private String dogPoster = " ";

    public String getDogPoster() {
        return dogPoster;
    }

    public Dog setDogPoster(String dogPoster) {
        this.dogPoster = dogPoster;
        return this;
    }

    public String getName() {
        return name;
    }

    public Dog setName(String name) {
        this.name = name;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public Dog setLocation(String location) {
        this.location = location;
        return this;
    }

    public String getPlayStyle() {
        return playStyle;
    }

    public Dog setPlayStyle(String playStyle) {
        this.playStyle = playStyle;
        return this;
    }

    public String getBread() {
        return bread;
    }

    public Dog setBread(String bread) {
        this.bread = bread;
        return this;
    }

    public float getEnergy() {
        return energy;
    }

    public Dog setEnergy(float energy) {
        this.energy = energy;
        return this;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public Dog setFavorite(boolean favorite) {
        isFavorite = favorite;
        return this;
    }
}

