package com.philco;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PhillipsDaramola on 19/10/2017.
 */

                // Check the oracle website for the requirements of how to create an immutable class.

    // Prevents the 'Location' class from being subclassed.
//public final class Location {
public class Location {

    // This is called defensive programming (because anything that isn't meant to be exposed to the outside isn't exposed).
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;

        // This will be executed as long as we are not adding null.
        if (exits != null){

            //this.exits = exits;

            // We're making the 'exits' immutable.
            // The hashmap for exits that are valid for a particular location.
            this.exits = new HashMap<>(exits);
        }
        // If we add null, this will create an empty hash map.
        else {
            this.exits = new HashMap<>();
        }

        // This automatically adds the ability to quit the program.
        // For each added location, we're automatically adding the ability to quit the program.
        this.exits.put("Q", 0);
    }

                    // We don't need this method any more - as we're now adding the exits to the constructor.
//    // Adding ability to add an exit.
//    public void addExit(String direction, int location){
//        exits.put(direction, location);
//    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        // return exits;
        // Instead of returning the exits map, we're creating a new hashmap and passing exits in the constructor.
        // A new map is created that has the new mapping from the 'exit' map.
        // This is done because nothing out of our class can change the above exit field, so this getter returns a copy of exit
        // so if the calling program want to add or remove mappings from it, then the 'exits' mapping field will not be affected.
        // This is a mutable object - i.e. it is changeable.
        return new HashMap<>(exits);
    }

    // Protected: Only available to classes in our package AND subclasses of Location.
    protected void addExit(String direction, int location){
        exits.put(direction, location);
    }
}



                        //--- MAPS ---//
                //-- ADVENTURE CHALLENGE --//

/*
public class Location {

    // This is called defensive programming (because anything that isn't meant to be exposed to the outside isn't exposed).
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationID, String description) {
        this.locationID = locationID;
        this.description = description;

        // The hashmap for exits that are valid for a particular location.
        this.exits = new HashMap<>();

        // This automatically adds the ability to quit the program.
        // For each added location, we're automatically adding the ability to quit the program.
        this.exits.put("Q", 0);
    }

    // Adding ability to add an exit.
    public void addExit(String direction, int location){
        exits.put(direction, location);
    }

    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        // return exits;
        // Instead of returning the exits map, we're creating a new hashmap and passing exits in the constructor.
        // A new map is created that has the new mapping from the 'exit' map.
        // This is done because nothing out of our class can change the above exit field, so this getter returns a copy of exit
        // so if the calling program want to add or remove mappings from it, then the 'exits' mapping field will not be affected.
        // This is a mutable object - i.e. it is changeable.
        return new HashMap<>(exits);
    }
}
*/
