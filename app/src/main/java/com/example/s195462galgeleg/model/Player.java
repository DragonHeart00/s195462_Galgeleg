package com.example.s195462galgeleg.model;

public class Player {

    private String name,email;
    private String score;

    public Player() {
    }

    public Player(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "\nplayerLog{" +
                ", name='" + name + '\'' +
                ", email=" + email +
                '}';
    }
}
