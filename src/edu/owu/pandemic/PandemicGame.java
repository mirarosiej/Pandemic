package edu.owu.pandemic;

//Main Game
public class PandemicGame{
    //this is the main
    public static void main(String[] args){
        System.out.println("- Start -");

        GameState gamestate = new GameState("cities.txt");

        gamestate.gameSetup();

        ioloop(gamestate);
    }

    static void ioloop(GameState gamestate){
        //TODO
    }
}



