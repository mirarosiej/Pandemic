package edu.owu.pandemic;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Scanner;

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
        Scanner reader = new Scanner(System.in);
        boolean looping = true;
        boolean success = true;
        ArrayList<Player> players= gamestate.getPlayers();

        System.out.print("\n\n");

        while(looping==true) {
            String move = "";
            System.out.print("Choose your move");
            move = reader.nextLine();
            for (Player player : players) {
                for (int i = 0; i < 4; i++) {
                    if (move.equals("drive")) {
                        //call the action drive
                        System.out.print("What is the destination");
                        String destination = reader.nextLine();
                        success = player.drive(destination);
                        if (success == false) {
                            System.out.println("Bad move");
                            i--;
                        }
                    }
                    if (move.equals("directFlight")) {
                        System.out.print("What is the destination");
                        String destination = reader.nextLine();
                        success = player.directFlight(destination);
                        if (success == false) {
                            System.out.println("Bad move");
                            i--;
                        }
                    }
                    if (move.equals("charterFlight")) {
                        System.out.print("What is the destination");
                        String destination = reader.nextLine();
                        success = player.charterFlight(destination);
                        if (success == false) {
                            System.out.println("Bad move");
                            i--;
                        }
                    }
                    if (move.equals("shuttleFlight")) {
                        System.out.print("What is the destination");
                        String destination = reader.nextLine();
                        success = player.shuttleFlight(destination);
                        if (success == false) {
                            System.out.println("Bad move");
                            i--;
                        }
                    }
                    if (move.equals("buildResearchStation")) {
                        success = player.buildResearchStation();
                        if (success == false) {
                            System.out.println("Bad move");
                            i--;
                        }
                    }
                    if (move.equals("treat")) {
                        System.out.print("What is the destination");
                        String destination = reader.nextLine();
                        player.treatDisease();
                        if (success == false) {
                            System.out.println("Bad move");
                            i--;
                        }
                    }
                    if (move.equals("share")) {
                        System.out.print("What is the destination");
                        String destination = reader.nextLine();
                        success = player.drive(destination);
                        if (success == false) {
                            System.out.println("Bad move");
                            i--;
                        }
                    }
                    if (move.equals("take")) {
                        System.out.print("What is the destination");
                        String destination = reader.nextLine();

                        System.out.print("What player?");
                        String pnum = reader.nextLine();

                        success = player.takeKnowledge(players.get(Integer.parseInt(pnum)), destination);
                        if (success == false) {
                            System.out.println("Bad move");
                            i--;
                        }
                    }
                    if (move.equals("discover")) {
                        System.out.print("What is the card1?");
                        String card1 = reader.nextLine();

                        System.out.print("What is the card2?");
                        String card2 = reader.nextLine();

                        System.out.print("What is the card3?");
                        String card3 = reader.nextLine();

                        System.out.print("What is the card4?");
                        String card4 = reader.nextLine();

                        System.out.print("What is the card5?");
                        String card5 = reader.nextLine();

                        success = player.discoverCure(card1, card2, card3, card4, card5);
                        if (success == false) {
                            System.out.println("Bad move");
                            i--;
                        }

                    }

                }
            }
        }
    }
}



