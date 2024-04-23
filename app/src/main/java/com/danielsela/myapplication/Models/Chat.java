package com.danielsela.myapplication.Models;

import java.util.List;

public class Chat {
    private String name = "";
    private String picture ="";
    private List<String> messages;

    public Chat(String name, String picture, List<String> messages) {
        this.name = name;
        this.picture = picture;
        this.messages = messages;
    }

    public Chat() {
    }

    public String getName() {
        return name;
    }

    public Chat setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getMessages() {
        return messages;
    }

    public Chat setMessages(List<String> messages) {
        this.messages = messages;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public Chat setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
