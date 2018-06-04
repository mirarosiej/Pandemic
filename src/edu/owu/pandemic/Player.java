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

    private PlayerCard getCityCardFromHand(String city){
        for (PlayerCard card : hand){
            if (card.getCity().equals(city)){
                return card;
            }
        }

        return null;
    }

    public boolean isHoldingCityCard(String target){
        for (PlayerCard card : hand){
            if (card.getCity().equals(target)){
                return true;
            }
        }

        return false;
    }

    //returns the card and removes it from hand
    private PlayerCard takeCityCard(String targetCity){
        PlayerCard toReturn = null;

        for (int i = 0; i < hand.size(); i++){
            PlayerCard card = hand.get(i);

            if (card.getCity().equals(targetCity)){
                toReturn = card;
                hand.remove(i);
            }
        }

        return toReturn;
    }

    public void addCardToHand(PlayerCard card){
        hand.add(card);
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

    //destroy a disease cube at the current position
    public void treatDisease(){
        City targetCity = GameState.getCities().get(currentCity);

        if (GameState.isDiseaseCured(targetCity.getColor())){
            targetCity.removeCubes(targetCity.getCubeCount());
        }else{
            targetCity.removeCubes(1);
        }
    }

    //share knowledge with another player
    //returns false if it isn't possible
    public boolean shareKnowledge(Player targetPlayer, String targetCity){

        boolean isResearcher = (role == Role.RESEARCHER);
        boolean inTargetCity = (targetCity.equals(currentCity));
        boolean playersShareCity = (targetPlayer.getCurrentCity().equals(currentCity));
        boolean haveTargetCityCard = (isHoldingCityCard(targetCity));

        if (haveTargetCityCard && playersShareCity && (isResearcher || inTargetCity)){
            //note: target player has to throw out a card if they have too many

            PlayerCard card = takeCityCard(targetCity);
            targetPlayer.addCardToHand(card);

            return true;
        } else {
            return false;
        }
    }

    //take knowledge from another player
    //returns false if it isn't possible
    public boolean takeKnowledge(Player targetPlayer, String targetCity){

        boolean isResearcher = (targetPlayer.getRole() == Role.RESEARCHER);
        boolean inTargetCity = (targetCity.equals(currentCity));
        boolean playersShareCity = (targetPlayer.getCurrentCity().equals(currentCity));
        boolean haveTargetCityCard = (targetPlayer.isHoldingCityCard(targetCity));

        if (haveTargetCityCard && playersShareCity && (isResearcher || inTargetCity)){
            //note: target player has to throw out a card if they have too many

            PlayerCard card = targetPlayer.takeCityCard(targetCity);
            addCardToHand(card);

            return true;
        } else {
            return false;
        }
    }

    //cure a disease by sacrificing 5 cards of the same color at a research station
    //returns false if it's not possible
    public boolean discoverCure(String cardCity1, String cardCity2, String cardCity3, String cardCity4, String cardCity5){

        boolean haveCards = true;
        String color = getCityCardFromHand(cardCity1).getColor();

        if (!isHoldingCityCard(cardCity1)){
            haveCards = false;
        }
        if (!isHoldingCityCard(cardCity2)){
            haveCards = false;
        }
        if (!isHoldingCityCard(cardCity3)){
            haveCards = false;
        }
        if (!isHoldingCityCard(cardCity4)){
            haveCards = false;
        }
        if (!isHoldingCityCard(cardCity5)){
            haveCards = false;
        }

        if(GameState.cityHasResearchStation(currentCity) && haveCards) {
            GameState.setCured(color);

            return true;
        }else{
            return false;
        }
    }

}
