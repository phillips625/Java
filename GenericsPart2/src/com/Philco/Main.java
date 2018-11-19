package com.Philco;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

// You want to be able to catch bugs early. Catching bugs early is less expensive than leaving it till when it gets to the live system.
// Catching the bugs at compile time is always better.
public class Main {

    public static void main(String[] args) {

        FootballPlayer joe = new FootballPlayer("Joe");
        BaseballPlayer pat = new BaseballPlayer("Pat");
        SoccerPlayer becks = new SoccerPlayer("Beckham");

        // This particular instance will only accept Football players.
        // Adding the type argument - football player.
        Team<FootballPlayer> adelaideCrows = new Team<>("Adelaide Crows");
        adelaideCrows.addPlayer(joe);
                // These are not football players
        // adelaideCrows.addPlayer(pat);
        // adelaideCrows.addPlayer(becks);

        System.out.println(adelaideCrows.numPlayers());

        Team<BaseballPlayer> baseballTeam = new Team<>("Chicago Cubs");
        baseballTeam.addPlayer(pat);

        // This would cause an error as you're trying to cast a String to a Player - see '((Player) player).getName()'
        // in the addPlayer method in the Team class. '.getName' is not a method in the String class.
        //Team<String> brokenTeam = new Team<>("This won't work.");
        // brokenTeam.addPlayer("No-one");

        Team<SoccerPlayer> brokenTeam = new Team<>("This won't work.");
        brokenTeam.addPlayer(becks);

        Team<FootballPlayer> melbourne = new Team<>("Melbourne");
        FootballPlayer banks = new FootballPlayer("Gordon");
        melbourne.addPlayer(banks);

        Team<FootballPlayer> hawthorn = new Team<>("Hawthorn");
        Team<FootballPlayer> fremantle = new Team<>("Fremantle");

        hawthorn.matchResult(fremantle, 1,0);
        hawthorn.matchResult(adelaideCrows, 3,8);

        adelaideCrows.matchResult(fremantle, 2,1);
        // This shouldn't be possible as adelaideCrows is not a Baseball team.
        // This would now give an error as we have now set the expected type to match  Team<FootballPlayer> adel...
        // in the Team class (public void matchResult(Team<T> opponent, int ou... - which specifies that 'baseballTeam' should be of
        // type 'FootballPlayer').
        // adelaideCrows.matchResult(baseballTeam, 1,1);

                            // Rankings
        System.out.println("Rankings");
        System.out.println(adelaideCrows.getName() + ": " + adelaideCrows.ranking());
        System.out.println(melbourne.getName() + ": " + melbourne.ranking());
        System.out.println(fremantle.getName() + ": " + fremantle.ranking());
        System.out.println(hawthorn.getName() + ": " + hawthorn.ranking());

                        // Comparing two teams
        // You want higher ranking teams to return -1 (as they are going to have lower numbers as they climb the table)

        // adelaideCrows has more points than both teams
        System.out.println(adelaideCrows.compareTo(melbourne));
        System.out.println(adelaideCrows.compareTo(hawthorn));
        // melbourne has the same no of points as fremantl.
        System.out.println(melbourne.compareTo(fremantle));
        // fremantle has less point that adelaideCrows
        System.out.println(fremantle.compareTo(adelaideCrows));

        /*
                                      // Collections class
             // This is one way of sorting all the teams in an array list - think premier league table.
        ArrayList<Team> teams;
        // The 'sort' method will automatically call the 'compareTo' method in the Team class.
        Collections.sort(teams);
        */
    }
}




















