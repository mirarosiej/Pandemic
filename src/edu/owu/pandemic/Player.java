package edu.owu.pandemic;

import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    ArrayList<PlayerCard> hand = new ArrayList<>();
    String currentCity = "Atlanta"; //default start location
    enum Role {OPERATION, MEDIC, PLANNER, DISPATCHER, SPECIALIST, RESEARCHER, SCIENTIST}
    Role role;

    public Player(Role playerole){
        role = playerole;
    }

    public Role getRole(){
        return role;
    }

    public String getCurrentCity(){
        return currentCity;
    }

    public void drawCard(Deck deck){
        PlayerCard card = (PlayerCard) deck.pop();
        hand.add(card);
    }

    public ArrayList<PlayerCard> getHand(){
        return hand;
    }

    public boolean isHoldingCityCard(String target){
        for (PlayerCard card : hand){
            if (card.getCity() == target){
                return true;
            }
        }

        return false;
    }

    private PlayerCard takeCityCard(String targetCity){
        PlayerCard toReturn = null;

        for (int i = 0; i < hand.size(); i++){
            PlayerCard card = hand.get(i);

            if (card.getCity() == targetCity){
                toReturn = card;
                hand.remove(i);
            }
        }

        return toReturn;
    }

    //move to an adjacent city
    //returns false if move isn't possible
    public boolean drive(String destination){
        HashMap<String, City> cities = GameState.getCities();

        if (cities.get(currentCity).isAdjacent(destination)){
            currentCity = destination; //do the move
            return true;
        }else{
            return false;
        }
    }

    //move to a city of a held card
    //returns false if move isn't possible
    public boolean directFlight(String destination){
        HashMap<String, City> cities = GameState.getCities();

        if (this.isHoldingCityCard(destination)){
            currentCity = destination; //do the move
            PlayerCard toDiscard = takeCityCard(destination);
            GameState.discardPlayerCard(toDiscard);
            return true;
        }else{
            return false;
        }
    }

}
