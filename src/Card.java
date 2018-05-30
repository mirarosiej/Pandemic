public abstract class Card {
    enum CardType {PLAYER, INFECTION, EPIDEMIC}
    CardType cardtype = null;

    public CardType getCardType(){
        return cardtype;
    }
}
