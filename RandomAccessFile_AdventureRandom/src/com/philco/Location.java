package com.philco;

// Serializable interface is in the 'java.io' package.
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by PhillipsDaramola on 19/10/2017.
 */

// Serialization: The process of translating a data structure or object into a format that can be stored and recreated is called serialization.
// A class can be made serializable (i.e. translate a class to a different format and back to its original format) by extending
// 'Serializable'.
// ALWAYS SET THE 'SERIAL VERSION UID' FIELD OURSELVES AS DIFFERENT COMPILERS HAVE DIFFERENT VALUES FOR THAT FIELD I.E ANDROID
// COMPILER ASSIGNS A DIFFERENT VALUE FROM A DESKTOP COMPILER. ALSO, THE FIELD SHOULD BE PRIVATE AS NO OTHER CLASS SHOULD USE IT.
// THINK ABOUT 'SERIAL VERSION UID' AS THE VERSION NUMBER FOR A CLASS. If we don't set it, the compiler will automatically set one
// for us (based on the number of field, no of methods, etc).
    // When the Location class is serialised, because the first two fields (locationID and description) are of primitive types,
    // they will automatically be serialised (aka converted to a format that can be stored in a file). Lucky enough, because the 'exits'
    // field also happens to implement Serializable, all three fields are serializable (no pun intended). If LinkedHashMap did not
    // implement Serializable, we'll be responsible for writing the code to store the class in a file ourselves. If a class we want to
    // serialisation has content from other classes, you have to make sure that the other classes are serializable themselves.
    // SERIALIZING A 'LOCATION' OBJECT TO A FILE WILL NOW BE STRAIGHT FORWARD.
public class Location implements Serializable{

    // This is called defensive programming (because anything that isn't meant to be exposed to the outside isn't exposed).
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    // THIS IS THE RECOMMENDED FIELD TO USE WITH SERIALIZABLE.
    // If this line is commented out, the main method will throw up an exception because the JVM already latched on to the serialVersionUID
    // of 1L and when you it can't find serialVersionUID number, the JVM creates one for the Location class (and of course, the one
    // create by the JVM will not match 1L).
    private long serialVersionUID = 1L;

    public Location(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;

        // This will be executed as long as we are not adding null.
        if (exits != null){

            //this.exits = exits;

            // We're making the 'exits' immutable.
            // The hashmap for exits that are valid for a particular location.
            this.exits = new LinkedHashMap<>(exits);
        }
        // If we add null, this will create an empty hash map.
        else {
            this.exits = new LinkedHashMap<>();
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
        // Hashmaps do not guarantee the ordering of their keys.
        // LinkedHashMaps guarantee the ordering of their lists.
        // RETURNS A DUPLICATE OF exits - which is made public.
        return new LinkedHashMap<>(exits);
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
