package com.example.hw2_war_316492644.Models;

import java.util.ArrayList;

public class Top10List {

    // Variables
    private final int LIST_SIZE = 10;

    private ArrayList<PlayerRecord> topPlayers;

    // Constructor
    public Top10List() {
        this.topPlayers  = new ArrayList<>();
    }

    //Functions

    public ArrayList<PlayerRecord> getTopPlayers() {
        return topPlayers;
    }

    public void addPlayerRecord(PlayerRecord playerRecord){
        topPlayers.add(playerRecord);
    }

    public Top10List setRecords(ArrayList<PlayerRecord> topPlayers) {
        this.topPlayers = topPlayers;
        return this;
    }
}
