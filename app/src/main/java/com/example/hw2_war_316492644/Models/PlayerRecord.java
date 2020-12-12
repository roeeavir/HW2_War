package com.example.hw2_war_316492644.Models;

import java.text.SimpleDateFormat;

public class PlayerRecord implements Comparable<PlayerRecord> {

    // Variables
    private String name;
    private int score;
    private String date;
    private double currentLongitude, currentLatitude;


    // Constructor
    public PlayerRecord(String name, int score, double currentLongitude, double currentLatitude) {
        this.name = name;
        this.score = score;
        this.currentLongitude = currentLongitude;
        this.currentLatitude = currentLatitude;
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss  dd.MM.yy");
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

    public double getCurrentLongitude() {
        return currentLongitude;
    }

    public double getCurrentLatitude() {
        return currentLatitude;
    }

    @Override
    public String toString() { // String to show on the top10 list
        return "name: " + name + '\n' +
                "score: " + score + '\n' +
                "time & date: " + date;
    }

    @Override
    public int compareTo(PlayerRecord playerRecord) { // Allows to sort top10 list by score
        return playerRecord.getScore() - this.getScore();
    }
}
