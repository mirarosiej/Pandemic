package edu.owu.pandemic;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {

    ArrayList<PlayerCard> hand = new ArrayList<>();
    String currentcity= "Atlanta";
    enum Role {OPERATION, MEDIC, PLANNER, DISPATCHER, SPECIALIST, RESEARCHER, SCIENTIST}
    Role role;

    public Player(Role playerole){
        role = playerole;
    }

    public Role getRole(){
        return role;
    }

    public String getCurrentcity(){
        return currentcity;
    }

    public void drawCard(Deck deck){
        PlayerCard card = (PlayerCard) deck.pop();
        hand.add(card);
    }

    public ArrayList<PlayerCard> getHand(){
        return hand;
    }

}
