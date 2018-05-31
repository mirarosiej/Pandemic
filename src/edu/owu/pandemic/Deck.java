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

    public Card draw(){
        Card card = pop();
        discard.add(card);
        return card;
    }

    //adding a card to the deck
    public void push(Card card){
        deck.add(card);
    }

    //used for shuffling the deck
    public void shuffle(){
        Collections.shuffle(deck);
    }

    //used for debug - prints everything to console
    public void printAllCards(){
        System.out.print("Cards in the deck: ");
        for (Card card : deck){
            System.out.print(card.getCardInfoString() + ", ");
        }

        System.out.print("\n");
    }


}



