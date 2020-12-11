package com.example.hw2_war_316492644.Models;

import java.util.ArrayList;
import java.util.Collections;

public class Top10List {

    // Variables
    private final int MAX_LIST_SIZE = 10;

    private ArrayList<PlayerRecord> topPlayers;

    // Constructor
    public Top10List() {
        this.topPlayers  = new ArrayList<>();
    }

    //Functions

    public ArrayList<PlayerRecord> getTopPlayers() {
        return topPlayers;
    }

    // Adds a player record if needed (depends on the score and max size of the list) and sorts the list.
    public void addPlayerRecord(PlayerRecord playerRecord){
        Collections.sort(this.topPlayers);
        if (topPlayers.size() < MAX_LIST_SIZE)
            topPlayers.add(playerRecord);
        else{
            if(topPlayers.get(MAX_LIST_SIZE-1).getScore() < playerRecord.getScore()){
                topPlayers.remove(MAX_LIST_SIZE-1);
                topPlayers.add(playerRecord);
            }
        }
        Collections.sort(this.topPlayers);
    }

    public void setTopPlayers(ArrayList<PlayerRecord> topPlayers) {
        this.topPlayers = topPlayers;
    }
}
