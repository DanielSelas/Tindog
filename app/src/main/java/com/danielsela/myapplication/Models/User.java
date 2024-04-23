package com.danielsela.myapplication.Models;

public class User {
    private static User instance;
    private String name ="";
    private String dogName ="";
    private String dogBreed ="";
    private String playStyle ="";
    private float energyLevel =0.0f;
    private String mail ="";

    private String profilePicutre ="";
    public User() {
    }

    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }

    public static void setInstance(User instance) {
        User.instance = instance;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getDogName() {
        return dogName;
    }
    public User setDogName(String dogName) {
        this.dogName = dogName;
        return this;
    }

    public String getDogBreed() {
        return dogBreed;
    }
    public User setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
        return this;
    }

    public String getPlayStyle() {
        return playStyle;
    }
    public User setPlayStyle(String playStyle) {
        this.playStyle = playStyle;
        return this;
    }

    public float getEnergyLevel() {
        return energyLevel;
    }
    public User setEnergyLevel(float energyLevel) {
        this.energyLevel = energyLevel;
        return this;
    }

    public String getMail() {
        return mail;
    }
    public User setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getProfilePicutre() {
        return profilePicutre;
    }
    public User setProfilePicutre(String profilePicutre) {
        this.profilePicutre = profilePicutre;
        return this;
    }
}
