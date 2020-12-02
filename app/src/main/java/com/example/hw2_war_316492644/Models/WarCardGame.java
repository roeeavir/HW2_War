package com.example.hw2_war_316492644.Models;

import java.util.ArrayList;
import java.util.Collections;

public class WarCardGame {// Card deck class
    // Variables
    private final int MAX_DIFFERENT_CARD_SCORES = 13; // There are 13 different card scores
    private final int MIN_CARD_SCORE = 2; // 2 is the lowest score a card can have
    private final char[] CARD_TYPES = {'c', 'd', 'h', 'l'}; // Stands for Clubs, Diamonds, Hearts, Leaves

    private ArrayList<WarCard> deck;
    private int leftScore;
    private int rightScore;
    private String[] turn_info;

    public WarCardGame(int leftScore, int rightScore) {
        this.leftScore = leftScore;
        this.rightScore = rightScore;
        this.deck = new ArrayList<>();
        turn_info = new String[3];
        createDeck();
        shuffleDeck();
    }

    private void createDeck() {// Function for initializing deck
        String str;
        for (char c : CARD_TYPES) {// inserts 52 cards into the deck
            for (int i = MIN_CARD_SCORE; i < MAX_DIFFERENT_CARD_SCORES + MIN_CARD_SCORE; i++) {
                str = "poker_" + c + i;
                deck.add(new WarCard(str, i));
            }
        }
    }

    public void playTurn() {
        turn_info = new String[3];// Will contain cards id and winner of this turn
        WarCard left_Card = deck.remove(0);// Gets card object from deck
        WarCard right_Card = deck.remove(0);
        turn_info[0] = left_Card.getCardName();// Gets card id from card object
        turn_info[1] = right_Card.getCardName();

        int left_Drawable_Value = left_Card.getCardValue();// Gets card value from card object
        int right_Drawable_Value = right_Card.getCardValue();


        if (left_Drawable_Value < right_Drawable_Value) {// Checks and updates round winner
            rightScore++;
            turn_info[2] = "Right";
        } else if (left_Drawable_Value > right_Drawable_Value) {
            leftScore++;
            turn_info[2] = "Left";
        } else
            turn_info[2] = "Draw";

    }

    private void shuffleDeck() {// Function for shuffling deck
        Collections.shuffle(this.deck);// Shuffles deck randomly
    }

    public String[] getWinner() {
        String[] strArr = new String[2];
        if (leftScore > rightScore) {// Checks winner
            strArr[0] = "Left Player";
            strArr[1] = "" + leftScore;
        }
        else if (leftScore < rightScore){
            strArr[0] = "Right Player";
            strArr[1] = "" + rightScore;
        }
        else{
            strArr[0] = "Corona";
            strArr[1] = "" + leftScore;
        }

        return strArr;
    }

    public int getFullDeckSize() {
        return CARD_TYPES.length * MAX_DIFFERENT_CARD_SCORES;
    }

    public ArrayList<WarCard> getDeck() {
        return deck;
    }

    public int getLeftScore() {
        return leftScore;
    }

    public int getRightScore() {
        return rightScore;
    }

    public void setLeftScore(int leftScore) {
        this.leftScore = leftScore;
    }

    public void setRightScore(int rightScore) {
        this.rightScore = rightScore;
    }

    public String[] getTurn_info() {
        return turn_info;
    }
}
