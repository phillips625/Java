package com.philco;

import java.io.*;
import java.util.*;

/**
 * Created by PhillipsDaramola on 20/11/2017.
 */

        // Advantages of dealing with byte stream:
    // We don't have to parse the various data type into the data type that was stored - aka we don't have to convert from one data type to another.


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

    // 'throws IOException' meaning no need to implement our finally and catch.
    // We don't have to set up 'finally' as try-with-resources will automatically close our files.
    public static void main(String[] args) throws IOException{

                            // WRITES FROM MEMORY INTO FILE.
        // The output file (locations.dat) will have a Binary format.
        // Chosen 'locations.dat' as it contains binary data.
        // 'FileOutputStream' actually opens the file.
        // DataOutputStream contains method write all primitive types: in this case, we have used writeInt to write integers in a binary format.
        // Although Strings are not primitive types, DataOutputStream contains a method, writeUTF, to write Strings.
        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))){
            for (Location location : locations.values()){

                // Since the first values in the locations_big.txt file is an integer, we're writing an integer first.
                locFile.writeInt(location.getLocationID());
                // writeUTF is used to write a String.
                locFile.writeUTF(location.getDescription());
                System.out.println("Writing location " + location.getLocationID() + " : " + location.getDescription());
                // Size of exits for each particular location.
                System.out.println("Writing " + (location.getExits().size() - 1) + " exits.");
                // Writing the size of the exits into file.
                locFile.write(location.getExits().size() - 1);
                // Going through the list of keys.
                for (String direction: location.getExits().keySet()){

                    // If we're not writing the quite location
                    if (!direction.equalsIgnoreCase("Q")){
                        // Couple of tabs.
                        System.out.println("\t\t" + direction + "," + location.getExits().get(direction));
                        locFile.writeUTF(direction);
                        locFile.writeInt(location.getExits().get(direction));
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

        // This outer try-catch block catches any other exceptions. In a real world application, we'll log the exceptions in a file.
        // You want to know the format in which the characters are arranged (in the original file) so that you can
        // read in the binary data.
        try (DataInputStream locFile = new DataInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))){
            // eof = End of file.
            boolean eof = false;
            while (!eof){
                // Nested try block - this try-catch block catches any end of file exceptions.
                try {
                    Map<String, Integer> exits = new LinkedHashMap<>();
                    // Read in the integer ID in the locations.dat file.
                    int locID = locFile.readInt();
                    // Read in the String description in the locations.dat file.
                    String description = locFile.readUTF();
                    // Read in the integer 'number of exits' in the locations.dat file.
                    int numExits = locFile.readInt();
                    System.out.println("Read Location " + locID + " : " + description);
                    System.out.println("Found " + numExits + " exits.");

                    for (int i = 0; i < numExits; i++){
                        String direction = locFile.readUTF();
                        int destination = locFile.readInt();
                        exits.put(direction, destination);
                        System.out.println("\t\t" + direction + "," + destination);
                    }

                    locations.put(locID, new Location(locID, description, exits));
                }

                // EOFException - End Of File exception. This will be triggered when we reach the end of the input from the 'locations.dat'
                // file.
                catch (EOFException e){
                    eof = true;
                }

            }
        }

        // Because we're not throwing an exception, we have to catch an exception.
        catch(IOException e){
            System.out.println("IO Exceptions");
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

/*



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


 */
