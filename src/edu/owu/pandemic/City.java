package edu.owu.pandemic;

import java.util.ArrayList;

public class City {
    private String name;
    private String color;

    private int cubeCount = 0;

    private ArrayList<String> adjacent = new ArrayList<String>();

    public City(String cty, String clr) {
        name = cty;
        color = clr;
    }

    public void addAdjacent(String adjcity){
        adjacent.add(adjcity);
    }

    public String getName(){
        return name;
    }

    public ArrayList<String> getAdjacent(){
        //return a copy of adjacent
        return new ArrayList<String>(adjacent); //copy constructor
    }

    public int getCubeCount() {
        return cubeCount;
    }

    public void setCubeCount(int cubes){
        cubeCount = cubes;
    }

    public void addCubes(int cubes){
        cubeCount += cubes;
    }
}
