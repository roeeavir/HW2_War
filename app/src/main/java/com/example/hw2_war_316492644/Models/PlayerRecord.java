package com.example.hw2_war_316492644.Models;

import java.text.SimpleDateFormat;

public class PlayerRecord {

    // Variables
    private String name;
    private int score;
    private String date;

    // Constructor
    public PlayerRecord(String name, int score) {
        this.name = name;
        this.score = score;
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy\nHH:mm:ss");
        this.date = format.format(System.currentTimeMillis());
    }

    // Functions

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
