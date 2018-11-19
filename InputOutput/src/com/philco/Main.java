package com.philco;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// BufferedReader - From file into memory (RAM)
// BufferedWriter - From RAM (memory) into file

public class Main {

    // Location is our class.
    // Key - Integer
    // Value - Location
    private static Locations locations = new Locations();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        Map<String, String> vocabulary = new HashMap<>();

        // These are the words we're going to be looking for.
        // The word "Quit" will get converted to "Q".
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        int loc = 1;
        while (true){
            // Get value of the current key (in locations.put(5, new Loc....).
            System.out.println(locations.get(loc).getDescription());

            // This shows the vulnerability in our code. The face that we can delete a something from a map is a problem.
            // This can be solved in the constructor by changing 'this.exits = exits;' to 'this.exits = new HashMap<>(exits);'.
            // Now, even though we tried to delete south, we can't because we have now created a new hashmap of exits in the constructor.
//            tempExit.remove("S");

            if (loc == 0) {
                // Breaks out the current loop
                break;
            }

                        // Printing out the exits that are valid for this location.
            // getExits - used to retrieve the map of valid exits from our current location.
            // loc - this is basically a key set to 1 above.
            // get(loc) is retrieving the location that has been set above like so (locations.put(0, new Loc..., locations.put(1, new Loc..., ).
            // getExits() gets a copy of the map of available exits for that particular location
            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are: ");

                        // Gets all the keys that indicate the directions that are valid.after this particular location.
            for (String exit: exits.keySet()){
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();

            // Make sure that there are more than one word.
            if (direction.length() > 1){

                // Split the content of the input - we're going to be splitting the words based on space.
                // Each individual words are then put into an array.
                String[] words = direction.split(" ");
                for (String word: words){
                    // Tests if a word is in the 'vocabulary' map defined above.
                    if (vocabulary.containsKey(word)) {

                        // The 'get' is used to retrieve the 'value' from the map of (that is associated with the word
                        // that passed the 'if' statement test by being a valid key ("NORTH", "SOUTH", etc)).
                        direction = vocabulary.get(word);

                        // 'break' breaks out of the immediate loop (the for loop in this case).
                        // After breaking out of the for loop, we should now be able to go in that direction.
                        break;
                    }
                }
            }

            if (exits.containsKey(direction)){
                // We're getting the integer corresponding to the direction based on the key that has been typed in.
                loc = exits.get(direction);
            }

            else {
                System.out.println("You cannot go in that direction.");
            }
        }
    }
}




/*




package com.philco;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    // Location is our class.
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

                // We're adding new locations. Keys 0,1,2..,5 gets converted to an Integer object (Autoboxing).
        // Adding key-value pairs.
        // Key = 0, Value = instance of Location class
        locations.put(0, new Location(0, "Exit"));
        locations.put(1, new Location(1, "Road"));
        locations.put(2, new Location(2, "Hill"));
        locations.put(3, new Location(3, "Building"));
        locations.put(4, new Location(4, "Valley"));
        locations.put(5, new Location(5, "Forest"));

        // Check the screenshot
        // We're mapping out each room.
        // 'gets' the first item in the locations map
        // These 4 items will allow us to move to the north, south, east and west directions.
        tempExit.put("W", 2);
        tempExit.put("E", 3);
        tempExit.put("S", 4);
        tempExit.put("N", 5);

                // We're removing the exits as it's redundant code and then adding it to the constructor.
        // The 5 'addExit' lines below are now replaced by one line in the constructor
        // This is an indication that we're finishing the game
        // tempExit.put("Q", 0);

                        // Mapping the rest of the rooms out.
        tempExit.put("N", 5);
        // tempExit.put("Q", 0);

        tempExit.put("W", 1);
        // tempExit.put("Q", 0);

        tempExit.put("N", 1);
        tempExit.put("W", 2);
        // tempExit.put("Q", 0);

        tempExit.put("S", 1);
        tempExit.put("W", 2);
        // tempExit.put("Q", 0);

        Map<String, String> vocabulary = new HashMap<>();

        // These are the words we're going to be looking for.
        // The word "Quit" will get converted to "Q".
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("WEST", "W");
        vocabulary.put("EAST", "E");

        int loc = 1;
        while (true){
            // Get value of the current key (in locations.put(5, new Loc....).
            System.out.println(locations.get(loc).getDescription());

            if (loc == 0) {
                // Breaks out the current loop
                break;
            }

                        // Printing out the exits that are valid for this location.
            // getExits - used to retrieve the map of valid exits from our current location.
            // loc - this is basically a key set to 1 above.
            // get(loc) is retrieving the location that has been set above like so (locations.put(0, new Loc..., locations.put(1, new Loc..., ).
            // getExits() gets a copy of the map of available exits for that particular location
            Map<String, Integer> exits = locations.get(loc).getExits();
            System.out.print("Available exits are: ");

                        // Gets all the keys that indicate the directions that are valid.after this particular location.
            for (String exit: exits.keySet()){
                System.out.print(exit + ", ");
            }
            System.out.println();

            String direction = scanner.nextLine().toUpperCase();

            // Make sure that there are more than one word.
            if (direction.length() > 1){

                // Split the content of the input - we're going to be splitting the words based on space.
                // Each individual words are then put into an array.
                String[] words = direction.split(" ");
                for (String word: words){
                    // Tests if a word is in the 'vocabulary' map defined above.
                    if (vocabulary.containsKey(word)) {

                        // The 'get' is used to retrieve the 'value' from the map of (that is associated with the word
                        // that passed the 'if' statement test by being a valid key ("NORTH", "SOUTH", etc)).
                        direction = vocabulary.get(word);

                        // 'break' breaks out of the immediate loop (the for loop in this case).
                        // After breaking out of the for loop, we should now be able to go in that direction.
                        break;
                    }
                }
            }

            if (exits.containsKey(direction)){
                // We're getting the integer corresponding to the direction based on the key that has been typed in.
                loc = exits.get(direction);
            }

            else {
                System.out.println("You cannot go in that direction.");
            }
        }
    }
}







 */