package com.Philco;

import java.util.ArrayList;

/**
 * Created by PhillipsDaramola on 07/10/2017.
 */
// ----- WORKING TOWARDS DISCRIMINATING BETWEEN TEAMS (YOU WANT THE PLAYERS TO BE PUT IN THE APPROPRIATE TEAM AS OPPOSED TO
// HAVING ALL THE PLAYERS IN ONE TEAM OBJECT) - SPECIFYING THE TEAM CLASS AS TYPE T ('Team<T>').
    // WHEN WE INSTANTIATE THIS CLASS, THE 'T' WILL AUTOMATICALLY BE REPLACED BY THE CLASS WE'RE INSTANTIATING WITH.
    // N.B - We changed the class type, the array list type and the argument to the addPlayer method to the generic type - this will
    // all be changed automatically to the class you're instantiating with. E.g 'FootballPlayer joe = new FootballPlayer("Joe");' will
    // replace <T> with <FootballPlayer>. <T> will be instantiated at run time.

// This is a base class - so we're not extending anything.

    // We're restricting the type of class we can use for this 'Team' class.
    // '<T extends Player>' RESTRICTS THE TYPE TO ONLY 'PLAYER' CLASSES OR CLASSES THAT INHERIT FROM THE PLAYER CLASS (E.G BaseballPlayer).
    // Team can also accept an INTERFACE. INTERFACES themselves can also accept type (<T>) parameter. In this case, the interface method
    // that you create would act on a specific type of object (in the class that implements the interface).
    // Also 'T' can also extend from a single class and multiple interfaces. You have to specify the class first and interfaces after.

    // 'Coach' and 'Manager' would be interfaces.
// public class Team<T extends Player & Coach & Manager>{
// 'Comparable' is an interface that has been created by Java. We'll be using the interface to compare our team to other teams.
    // The 'Team<T>' in the interface is to ensure that we can't compare a Baseball team to a Football team.
    // We can't still do only <Team>, but we don't know what type of teams we are passing.
public class Team<T extends Player> implements Comparable<Team<T>>{

    // This is a team's name.
    private String name;
    int played = 0;
    int won = 0;
    int lost = 0;
    int tied = 0;

    //      These 2 lists are not the same.
    // List<SoccerPlayer>
    // List<FootballPlayer>

    // We're using a GENERICS of type player.
    private ArrayList<T> members = new ArrayList<>();
    // private ArrayList<Player> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // 'T' MAKES THE METHOD MORE GENERIC - MEANING, WE CAN NOW ADD ALL TYPES OF PLAYERS.
    // public boolean addPlayer(Player player){
    public boolean addPlayer(T player){
        // You don't want a duplicate.
        if (members.contains(player)){

            // Now we're using the generic type, T, we have to cast the player object.
            // System.out.println(((Player) player).getName() + " is already on this team.");
            System.out.println(player.getName() + " is already on this team.");
            return false;
        }
        else {
            members.add(player);
            // System.out.println(((Player) player).getName() + " picked for team " + this.name);
            System.out.println(player.getName() + " picked for team " + this.name);
        }

        return false;
    }

    // Returns the number of players on the team.
    public int numPlayers(){
        return this.members.size();
    }

    // This shouldn't be possible as adelaideCrows is not a Baseball team.
    // adelaideCrows.matchResult(baseballTeam, 1,1);
    // public void matchResult(Team opponent, int ourScore, int theirScore){

    // Returns whether the team won or lost. THIS SAVES THE RESULTS FOR OUR TEAM.
    public void matchResult(Team<T> opponent, int ourScore, int theirScore){

        String message;

        if (ourScore > theirScore){
            won++;
            message = " beat ";
        }
        else if (ourScore == theirScore){
            tied++;
            message = " drew with ";
        }
        else {
            lost++;
            message = " lost to ";
        }
        // Counts the number of played games.
        played++;

        // 'matchResult' method - This takes care of the team the match result is called from (e.g 'adelaideCrows' in
        // the main function) but not the opponent.

        // THIS SAVES THE RESULTS FOR THE OPPONENT'S TEAM - SAVING THE RESULT IN A SEPARATE OBJECT.
        // This saves the result in the opponent's team as well.
        if (opponent != null){
            // 'opponent.getName()' is done here because in the next line, the opponent is set to null - hence this if statement will
            // not be executed.
            System.out.println(this.getName() + message + opponent.getName());
            // We're saving our result for our team and the opponent's result as well.
            // We're setting the opponent to null so this if statement is not executed again.
            // null is specified in order to avoid going in an infinite loop.
            opponent.matchResult(null, theirScore, ourScore);
        }
    }

    public int ranking(){
        return (won * 2) + tied;
    }

    // The String class also implements the 'compareTo' method.
    @Override
    public int compareTo(Team<T> team) {
        // Our team/object is ranked higher.
        // This is the current object we're comparing.
        if (this.ranking() > team.ranking()){
            return -1;
        }

        // Our team/object is ranked lower.
        else if (this.ranking() < team.ranking()){
            return 1;
        }
        // The ranking are the same in this case.
        else {
            return 0;
        }

    }

}
















