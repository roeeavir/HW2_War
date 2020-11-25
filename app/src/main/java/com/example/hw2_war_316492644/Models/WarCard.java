package com.example.hw2_war_316492644.Models;

public class WarCard {// Class for implementing card ID and value
    // Variables
    final private String cardName;
    final private int cardValue;

    // Constructor
    public WarCard(String card_Name, int card_Value) {
        this.cardName = card_Name;
        this.cardValue = card_Value;
    }

    public String getCardName() {
        return cardName;
    }// Get card ID

    public int getCardValue() {
        return cardValue;
    }// Get card value


}
