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
        if (this.isHoldingCityCard(destination)){
            currentCity = destination; //do the move
            PlayerCard toDiscard = takeCityCard(destination);
            GameState.discardPlayerCard(toDiscard);
            return true;
        }else{
            return false;
        }
    }

    //move to any city if card matches current position
    //returns false if move isn't possible
    public boolean charterFlight(String destination){
        if (this.isHoldingCityCard(currentCity)){
            String previousCity = currentCity;
            currentCity = destination; //do the move
            PlayerCard toDiscard = takeCityCard(previousCity);
            GameState.discardPlayerCard(toDiscard);
            return true;
        }else{
            return false;
        }
    }

    //move between research station cities
    //returns false if move isn't possible
    public boolean shuttleFlight(String destination){
        //if current location and destination both have research stations
        if (GameState.cityHasResearchStation(currentCity) && GameState.cityHasResearchStation(destination)){
            currentCity = destination; //do the move
            return true;
        }else{
            return false;
        }
    }

    //build research station at current location
    //returns false if not possible
    public boolean buildResearchStation(){
        if (role == Role.OPERATION){ //if the player is an operations expert
            GameState.placeResearchStation(currentCity);

            return true;
        }else if (this.isHoldingCityCard(currentCity)){ //if the player has a card of their current position
            GameState.placeResearchStation(currentCity);

            PlayerCard toDiscard = takeCityCard(currentCity);
            GameState.discardPlayerCard(toDiscard);

            return true;
        }else{
            return false;
        }
    }

}
