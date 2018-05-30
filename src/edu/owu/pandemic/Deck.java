package edu.owu.pandemic;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    //These are the decks
    ArrayList<Card> deck;
    ArrayList<Card> discard;

    public Deck(){
        deck = new ArrayList<Card>();
        discard = new ArrayList<Card>();
    }

    //retrieves the top card and returns it
    public Card pop(){
        return deck.remove(deck.size()-1);
    }

    //adding a card to the deck
    public void push(Card card){
        deck.add(card);
    }

    //used for shuffling the deck
    public void shuffle(ArrayList<Card>List, Random rnd){
        Collections.shuffle(List);
    }
}



