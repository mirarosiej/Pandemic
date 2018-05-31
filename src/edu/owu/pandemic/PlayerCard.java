package edu.owu.pandemic;

public class PlayerCard extends Card {
    private String city;
    private String color;

    public PlayerCard(String cityname, String colorstring){
        city = cityname;
        color = colorstring;
        cardtype = CardType.PLAYER;
    }

    public String getCardInfoString(){
        return "edu.owu.pandemic.Player card - " + city + ":" + color;
    }

    public String getColor(){
        return color;
    }

    public String getCity(){
        return city;
    }
}
