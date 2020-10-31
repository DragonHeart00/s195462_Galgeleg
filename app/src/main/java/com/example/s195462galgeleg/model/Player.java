package com.example.s195462galgeleg.model;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


//create database
@Entity (tableName = "playList")
public class Player {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="name")
    private String name;
    @ColumnInfo(name="score")
    private String score;
    @ColumnInfo(name="date")
    private String date;

   /*
   public Player(String name, String score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }
    */

    // for now i will use this constructor, because i did not define the score in MainActivity
    public Player(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
