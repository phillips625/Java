package com.Philco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by PhillipsDaramola on 09/10/2017.
 */

// Only classes with a team specified can use this class - e.g LaLiga<Barcelona>
    // League is a parametised type bound by the team class

    // This is done to force people to use the generic type.
// public class League<U extends Player, T extends Team> {

public class League<T extends Team> {

    // Name of the league
    public String name;
    private ArrayList<T> league = new ArrayList<T>();

    public League(String name) {
        this.name = name;
    }

    // To add teams to the league.
    public boolean add(T team){

        if (league.contains(team)){
            // Team already exists in the league.
            return false;
        }

        league.add(team);
        return true;
    }

               // Show league table.
    // The 'sort' method uses the 'compareTo' method (which we had to implement in the Team class because we added the 'Comparable'
    // interface).
    public void showLeagueTable(){
        Collections.sort(league);

        // Printing off the sorted league
        for (T t : league){
            // getName and ranking are methods in the Team class.
            System.out.println(t.getName() + ": " + t.ranking());
        }
    }

}




















