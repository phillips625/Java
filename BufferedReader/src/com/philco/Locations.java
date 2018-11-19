package com.philco;

import java.io.*;
import java.util.*;

/**
 * Created by PhillipsDaramola on 20/11/2017.
 */

// GOOGLE: Try-with-resources to make your life easier.

// We can either 1) throw an exception OR 2) catch an exception.
    // Since in the main method, we throwing an exception, we can now comment out the catch block (because you can either throw an exception
// or catch an exception - in this case we're throwing an exception).
    // In the main function, we're throwing an exception - which means if a method calls the main method, that method must catch the IOException
    // exception. While in the try-catch-finally blocks we have implemented, we're catching exceptions that other method have added to there methods.

// This 'Locations' class behaves like a Map.
public class Locations implements Map<Integer, Location> {

    // Stores the locations
    private static Map<Integer, Location> locations = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException{

                            // WRITES FROM MEMORY INTO FILE.

        // USing a buffered writer instead
//      try (FileWriter locFile = new FileWriter("locations.txt");
        try (BufferedWriter locFile = new BufferedWriter( new FileWriter("locations.txt"));
             BufferedWriter dirFile = new BufferedWriter( new FileWriter("directions.txt"))){
            for (Location location : locations.values()){
                // Writing to a file.
                locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
                // Going through all the exits for a given location.
                for (String direction : location.getExits().keySet()){

                    // To make the two files the same, we have to remove the bit in the 'directions.txt' file that has 'Q' aka Quit as the direction
                    // 'equalsIgnoreCase' igones the case of the letter in question (Q in this case).
                    if (!direction.equalsIgnoreCase("Q")){
                        // 'get' returns the value of the 'direction' key.
                        dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
                    }
                }
            }
        }

    }

                        // READ FROM FILE INTO MEMORY.

    // Static Initialisation Block will be executed only once (that is, when the Locations class is loaded).
    // The data initialised in this block of code would only be initialised once.

    // We always have to initialise the try-catch block in a static block (as we cannot use 'throw IOExceptions' as we did in the main function -
    // remember, you don't have to catch any exceptions if you have 'throw IOExceptions' in front of any function).
    // The code in the SIB is loaded first before the code in the main method. It means that the locations_big.txt and directions_big.txt files have
    // been read in before the main block is executed.
    static {
        //  BufferedReader reads chunks of data at a time.
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader("locations_big.txt")))){
            scanner.useDelimiter(",");

            // We're going to keep looping while we have some data in the file.
            while (scanner.hasNextLine()){
                // The first bit of information in the 'locations.txt' file is an integer.
                int loc = scanner.nextInt();
                // The int 'loc' was retrieved, then we moved past the comma (which was the delimiter) and finally we continue on to the final
                // description.
                scanner.skip(scanner.delimiter());
                // Here we're retrieving the description and moving the scanner to the next line.
                String description = scanner.nextLine();

                // Just checking the we're importing the correct descriptions - we won't normally put the sout in live code.
                System.out.println("imported loc: " + loc + ": " + description);

                // Object to store the keys and values wr're getting from the file reader.
                Map<String, Integer> tempExit = new HashMap<>();

                locations.put(loc, new Location(loc, description, tempExit));

            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        // We don't need the scanner anymore, we can just use a BufferedReader object.
        try(BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))){

            String input;
            // While scanner still has something in it.
            while ((input = dirFile.readLine()) != null){

                // READING AN ENTIRE LINE INSTEAD OF READING CHARACTER-BY-CHARACTER (from lines 194 to 203).
//                String input = scanner.nextLine();
                // This creates a string array of characters, whilst eliminating the delimiter (,). All the characters in that line will
                // now be stored in an array.
                String[] data = input.split(",");
                int loc = Integer.parseInt(data[0]);
                String direction = data[1];
                int destination = Integer.parseInt(data[2]);


                // 'destination': JVM handles the unboxing of 'destination'
                System.out.println(loc + ": " + direction + ": " + destination);

                // Getting the value of each 'loc'
                Location location = locations.get(loc);
                location.addExit(direction, destination);
            }
        }

        catch (IOException e){
            e.printStackTrace();
        }

    }

    // Basically using the methods in the HashMap class.
    @Override
    public int size() {
        return locations.size();
    }

    @Override
    public boolean isEmpty() {
        // Returns true or false depending on if the locations map has something in it.
        return locations.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        return locations.put(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override
    public void putAll(Map<? extends Integer, ? extends Location> m) {

    }

    @Override
    public void clear() {

        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
