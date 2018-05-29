//This will be the Card class for the player cards and the infection cards

public abstract class Card{
  public static enum CardTypes {Player, Infection, Epidemic}
  private String city;
  private CardTypes cardType;
  
  public void setCardType(CardTypes cardType){
    this.cardType = cardType;
  }
  
  public void getCardType(){
    return cardType; 
  }
  
  public String getCity(){
    return city;
  }
  
  public String setCity(String city){
    this.city = city;
  }
}
