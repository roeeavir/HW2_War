package com.example.hw2_war_316492644.Models;

import java.util.ArrayList;

public class Top10List {

    // Variables
    private ArrayList<PlayerRecord> topPlayers = new ArrayList<>();

    // Constructor
    public Top10List(ArrayList<PlayerRecord> topPlayers) {
        this.topPlayers = topPlayers;
    }

    //Functions

    public ArrayList<PlayerRecord> getTopPlayers() {
        return topPlayers;
    }

    public Top10List setRecords(ArrayList<PlayerRecord> topPlayers) {
        this.topPlayers = topPlayers;
        return this;
    }
}
