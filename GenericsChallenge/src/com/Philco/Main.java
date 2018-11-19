package com.Philco;

            // Generic Type - Used for passing classes / better still, specific classes (i.e. Football class should be viewed
            // differently from a Baseball class by the Team class, despite the fact that they are both teams) to another class.
public class Main {

    public static void main(String[] args) {

        // This is done to make sure people specify the generic type in the team class
        // League<FootballPlayer, Team<FootballPlayer>> rawLeague = new League<>("Raw");

        // The Team class is also parameterised - hence the reason why we had to specify the 'FootballPlayer' class.
        League<Team<FootballPlayer>> footballLeague = new League<>("AFL");

// This particular instance will only accept Football players.
        // Adding the type argument - football player.
        Team<FootballPlayer> adelaideCrows = new Team<>("Adelaide Crows");
        Team<FootballPlayer> melbourne = new Team<>("Melbourne");
        Team<FootballPlayer> hawthorn = new Team<>("Hawthorn");
        Team<FootballPlayer> fremantle = new Team<>("Fremantle");

        Team<BaseballPlayer> baseballTeam = new Team<>("Chicago Cubs");

                // Results
        hawthorn.matchResult(fremantle, 1,0);
        hawthorn.matchResult(adelaideCrows, 3,8);
        adelaideCrows.matchResult(fremantle, 2,1);

        footballLeague.add(adelaideCrows);
        footballLeague.add(melbourne);
        footballLeague.add(hawthorn);
        footballLeague.add(fremantle);

        // This will give an error because baseballTeam is of type 'BaseballPlayer' and we are trying to add it to
        // a class of type 'FootballPlayer'.
        // footballLeague.add(baseballTeam);

                  // League table
        footballLeague.showLeagueTable();
    }
}
