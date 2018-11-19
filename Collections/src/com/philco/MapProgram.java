package com.philco;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by PhillipsDaramola on 18/10/2017.
 */

// 'HashMap' and 'LinkedMap' implements the 'MapProgram' interface.
// MapProgram uses 'key' - value pair. One key maps to one value ONLY.

public class MapProgram {

    // Type 'PSVM' to create the main method.
    public static void main(String[] args) {

        // We're going it this was because there is a conflict between our class name (MapProgram) and the MapProgram (we're
        // declaring which is built by java). This is a way of getting round the fact that you've used a reserved word (MapProgram) for the name of
        // a class.
        // We changed the class name from 'MapProgram' to 'MapProgram' to avoid type this.
        
        // java.util.MapProgram<String, String> languages = new HashMap<>();


        // Generic parameters, 'String, String', denoted the key and the value we'll be passing to the 'Map' class.
        Map<String, String> languages = new HashMap<>();

        // First parameter is the key and the second parameter is the value.
        languages.put("Java", "First");
        languages.put("Python", "Second");
        languages.put("CSharp", "Third");
//        languages.put("CPlusPlus", "Fourth");
//        languages.put("Javascript", "Fifth");

        // 'put' can tell you if a value has been assigned to a key before.
        // We get 'null' in both cases - meaning that this was a brand new key-value pair that was added to the
        // dictionary.
        System.out.println(languages.put("CPlusPlus", "Fourth"));
        System.out.println(languages.put("Javascript", "Fifth"));

//        // languages.get("Java") retrieves the value of the "Java" key.
//        System.out.println(languages.get("Java"));
//
//


        // This prevents you from adding a new value to a key that already exists.
        if (languages.containsKey("Java")){
            System.out.println("Java is already in the map");
        }

        else {
            // The value of the 'Java' key can be overridden
            languages.put("Java", "First Java override");
        }
        // This first sout prints out the previous value of "Java".
        // This is how you know that a key has a new value
//        System.out.println(languages.put("Java", "First Java override"));
//        // This prints out the new value.
//        System.out.println(languages.get("Java"));


        System.out.println("======================================");

                    // Removing Keys
        // Python 'key' and its associated value is removed from the map.
        // We can remove a key only if it's mapped to a certain value.
//        languages.remove("Python");

        // CPlusPlus wasn't removed because the key-value pair didn't match the original.
        // NB: The remove method returns true or false.
        // if (languages.remove("CPlusPlus", "This is not the real value")){

        // CPlusPlus is now removed as the key-value pair matches the original.
        if (languages.remove("CPlusPlus", "Fourth")){
            System.out.println("CPlusPlus removed");
        }
        else {
            System.out.println("CPlusPlus not removed");
        }

                        // Replace method
        // Values are only replaced when the key is present in the map.
        // System.out.println(languages.replace("Python", "Not replaced item"));

        // Returns null because 'Pythonski' doesn't exist.
        // System.out.println(languages.replace("Pythonski", "Not replaced item"));


                            // OR
        // This can be useful when replacing maiden names to the new surname - you need to identify
        // the correct record to make this change on.
        if (languages.replace("Javascript", "Fifth", "New JS value")){
            System.out.println("Javascript value replace");
        }
        else {
            System.out.println("JS value is not replace");
        }


        // Returns all the keys and values in our 'Map'.
        // With Maps, the key-value pairs are not returned in order.
        for (String key: languages.keySet()){
            System.out.println(key + " : " + languages.get(key));
        }
    }
}























