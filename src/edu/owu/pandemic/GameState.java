package edu.owu.pandemic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GameState {
    private HashMap<String, City> nodes = new HashMap<String, City>();
    private Deck infectiondeck = new Deck();
    private Deck playerdeck = new Deck();

    private int outbreak = 0;
    private int infectionrateindex = 0;
    private int[] infectiorates = new int[]{2,2,2,3,3,4,4};

    //constructor
    public GameState(String info_file){
        parseInfo(info_file);
    }

    //add city to the list
    private void addNode(City node){
        nodes.put(node.getName(), node);
    }

    //read all the cities and other board info from text file
    private void parseInfo(String filename){

        List<String> lines = null;

        try{
            Scanner input = new Scanner(new FileInputStream(new File(filename)));
            lines = Files.readAllLines(Paths.get(filename));
        } catch(IOException e){
            e.printStackTrace();
        }

        //save all the cities
        //and add their cards
        for (int i=0; i < lines.size(); i++ ){
            //if this line isn't empty
            if (lines.get(i).length() > 2) {
                List<String> words = new ArrayList<String>(Arrays.asList(lines.get(i).split(" ")));

                String color = words.get(0);
                String cityName = words.get(1);

                City newCity = new City(cityName, color);
                addNode(newCity);

                //add cards for this city
                infectiondeck.push(new InfectionCard(cityName, color));
                playerdeck.push(new PlayerCard(cityName, color));
            }
        }

        //now connect all the cities
        for (int i=0; i < lines.size(); i++ ){
            //if this line isn't empty
            if (lines.get(i).length() > 2) {
                List<String> words = new ArrayList<String>(Arrays.asList(lines.get(i).split(" ")));

                String cityName = words.get(1);
                City toUpdate = nodes.get(cityName);

                //for each adjacent city
                for (int j = 2; j < words.size(); j++){
                    String adjCityName = words.get(j);

                    //make sure that we didn't miss this adjacent city when parsing
                    assert (nodes.containsKey(adjCityName));

                    toUpdate.addAdjacent(adjCityName);
                }

                //DEBUG-START
                System.out.print("The city " + toUpdate.getName() + " is adjacent to: ");
                for (String adj : toUpdate.getAdjacent()){
                    System.out.print(adj + ", ");
                }
                System.out.print("\n");
                //DEBUG-START
            }
        }

        //DEBUG-START
        System.out.print("\n");
        System.out.print("Cards in the Infection deck: ");
        infectiondeck.printAllCards();
        System.out.print("Cards in the Player deck: ");
        playerdeck.printAllCards();
        System.out.print("\n");
        //DEBUG-END
    }
}
