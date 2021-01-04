package com.example.s195462galgeleg.model;

public class Player {

    private String name,email;
    private int score;


   /*
   public Player(String name, String score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }
    */

    // for now i will use this constructor, because i did not define the score in MainActivity



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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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
