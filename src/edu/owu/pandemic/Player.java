package edu.owu.pandemic;

import java.util.ArrayList;

public class Player {

    ArrayList<PlayerCard> hand = new ArrayList<>();
    String currentcity= "Atlanta";
    enum Role {OPERATION, MEDIC, PLANNER, DISPATCHER, SPECIALIST, RESEARCHER, SCIENTIST}
    Role role;

    public Player(Role playerole){
        role = playerole;

    }

}
