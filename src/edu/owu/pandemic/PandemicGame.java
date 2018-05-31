package edu.owu.pandemic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


//Main Game
public class PandemicGame{
    //this is the main
    public static void main(String[] args){
        System.out.println("- Start -");

        Deck infectiondeck = new Deck();
        Deck playerdeck = new Deck();

        int outbreak = 0;
        int[] infectionrate = new int[]{2,2,2,3,3,4,4};

        try{
            String filename = "cities.txt";
            Scanner input = new Scanner(new FileInputStream(new File (filename)));
            List<String> lines = Files.readAllLines(Paths.get(filename));
            for (int i=0; i < lines.size(); i++ ){
                //List<String> words = Arrays.asList(lines[i].split("\\s* \\s*"));
                List<String> words = new ArrayList<String>(Arrays.asList(lines.get(i).split(" ")));

                String color = words.get(0);
                String cityName = words.get(1);

                City newcity = new City(cityName, color);
            }

        } catch(IOException e){
            e.printStackTrace();
        }
    }
}
