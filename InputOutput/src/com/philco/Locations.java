package com.philco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    private static Map<Integer, Location> locations = new HashMap<>();

    public static void main(String[] args) throws IOException{

                    // USING Try-with-resources
        // This is an example of try-with-resources. It automatically handles any exceptions and automatically closes the file for us. Also,
        // notice that we also do not need a finally block.
        // This works a little differently behind the scenes from try-catch-finally. Here, it will print out the first error encountered.
        // In the case of try-finally, the first error will be hidden by the exception.
        // An added advantage of using the try-with-resources: You can add multiple Filewriter objects.
    try (FileWriter locFile = new FileWriter("locations.txt");
         FileWriter dirFile = new FileWriter("directions.txt")){
        for (Location location : locations.values()){
            // Writing to a file.
            locFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
            // Going through all the exits for a given location.
            for (String direction : location.getExits().keySet()){
                // 'get' returns the value of the 'direction' key.
                dirFile.write(location.getLocationID() + "," + direction + "," + location.getExits().get(direction) + "\n");
            }
        }
    }





                            //OR

                        // OLD WAY - Try and Finally

//        FileWriter localFile = null;
//
//        // A try block requires a catch block or a finally block or both. A finally block will be executed no matter what. So if a line of code throws
//        // an exception, the exception block will be executed, but the finally, the finally block will be executed.
//        try {
//            // Here, we're writing data to a stream.
//            // There are 2 different types of exceptions:
//            // Checked and Unchecked exceptions.
//            // FileWriter is an example of a 'Checked' exception - because it has an 'IOExceptions' which we have to catch.
//            // 'locations.txt' will be created.
//            localFile = new FileWriter("locations.txt");
//
//            // Loops through all the 'Location' values in the 'locations' Map declared above.
//            for (Location location: locations.values()){
//                // If there was an exception thrown here, 'localFile.close();' will not be executed (because the try block will be exited).
//                // This is ofcourse a problem as we always need to close every file stream we open.
//                localFile.write(location.getLocationID() + "," + location.getDescription() + "\n");
//
//                // You don't want to leave your 'throw' statement in live production code!!
//                // An exception will be thrown after the first iteration (by the statement below). JVM then jumps to the finally block and
//                // executes the statements there - the finally block is executed no matter what.
////                throw new IOException("Test exception thrown while writing");
//            }
//        }
//
//        // When we created a folder name (by mistake) as locations.txt, and we ran the programme, FileNotFoundException was caught despite the
//        // fact that we have only specified that IOException be caught. FileNotFoundException was caught (when you would have taught it
//        // shouldn't be) because FileNotFoundException is a subclass of the IOEXception class.
//        // We're commenting the catch part of the code because we're now throwing an exception instead.
////        catch (IOException e){
////            System.out.println("In Catch Block");
////            e.printStackTrace();
////        }
//
//        finally {
//            System.out.println("In the finally block");
//            // We're adding a try-catch block because the close function throws an 'IOException' exception.
//            // We can now remove the try keyword since we are now throwing the IOEXception exception (and the catch block has been removed).
////            try {
//            // This is defensive programming. We're making sure that we're reducing the chances of the code crashing.
//            if (localFile != null) {
//                System.out.println("Attempting to close localFile");
//                // Closing the file stream is really important and if not taken care of, can cause resource leaks and locked files.
//                localFile.close();
//            }
////            }
//
//            // We're commenting the catch part of the code because we're now throwing an exception instead.
////            catch (IOException e){
////                e.printStackTrace();
////            }

//        }
    }

    // Static Initialisation Block will be executed only once (that is, when the Locations class is loaded).
    // The data initialised in this block of code would only be initialised once.

    // We always have to initialise the try-catch block in a static block (as we cannot use 'throw IOExceptions' as we did in the main function -
    // remember, you don't have to catch any exceptions if you have 'throw IOExceptions' in front of any function).
    static {

        // WE'RE READING FROM A FILE - UNLIKE WHEN WE WERE READING FROM A FILE.
        // The Long winded method.
        // Scanner scanner = null;

        // READING THE EXITS USING FileReader (WITHOUT TRY-WITH-RESOURCES)
        try (Scanner scanner = new Scanner(new FileReader("locations_big.txt"))){
            // locations.txt is where we're getting the data from.
            // Scanner retrieves data from file instead of a keyboard.

            // scanner = new Scanner(new FileReader("locations.txt"));
            // Showing the disadvantage of using this method for reading big files
            //scanner = new Scanner(new FileReader("locations_big.txt"));

            // This is a way of telling the scanner that our information is separated by a comma.
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

        // Close now happens automatically.

//        finally {
//            if (scanner != null){
//                // This automatically closes the file for us.
//                scanner.close();
//            }
//        }



                                // OR




                        // READING THE EXITS USING FileReader (WITHOUT TRY-WITHOUT-RESOURCES)

//        // WE'RE READING FROM A FILE - UNLIKE WHEN WE WERE READING FROM A FILE.
//        // The Long winded method.
//        Scanner scanner = null;
//
//        // READING THE EXITS USING FileReader (WITHOUT TRY-WITH-RESOURCES)
//        try {
//            // locations.txt is where we're getting the data from.
//            // Scanner retrieves data from file instead of a keyboard.
//
//            // scanner = new Scanner(new FileReader("locations.txt"));
//            // Showing the disadvantage of using this method for reading big files
//            scanner = new Scanner(new FileReader("locations_big.txt"));
//
//            // This is a way of telling the scanner that our information is separated by a comma.
//            scanner.useDelimiter(",");
//
//            // We're going to keep looping while we have some data in the file.
//            while (scanner.hasNextLine()){
//                // The first bit of information in the 'locations.txt' file is an integer.
//                int loc = scanner.nextInt();
//                // The int 'loc' was retrieved, then we moved past the comma (which was the delimiter) and finally we continue on to the final
//                // description.
//                scanner.skip(scanner.delimiter());
//                // Here we're retrieving the description and moving the scanner to the next line.
//                String description = scanner.nextLine();
//
//                // Just checking the we're importing the correct descriptions - we won't normally put the sout in live code.
//                System.out.println("imported loc: " + loc + ": " + description);
//
//                // Object to store the keys and values wr're getting from the file reader.
//                Map<String, Integer> tempExit = new HashMap<>();
//
//                locations.put(loc, new Location(loc, description, tempExit));
//
//            }
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//
//        finally {
//            if (scanner != null){
//                // This automatically closes the file for us.
//                scanner.close();
//            }
//        }

        // READING THE EXITS USING BufferReader (WITHOUT TRY-WITH-RESOURCES)
        // BufferReader: Rather than reading one character at a time (using FileReader), BufferReader reads a bunch of characters at a time (the
        // default size of characters that it read at a time is 8k bytes). Since the size of our file is less than 8K bytes, BufferReader will read
        // all the contents in that file in one go.
        // try(Scanner scanner = new Scanner(new BufferedReader(new FileReader("directions_big.txt")))){

                        // OR
        // We don't need the scanner anymore, we can just use a BufferedReader object.
        try(BufferedReader dirFile = new BufferedReader(new FileReader("directions_big.txt"))){

            String input;
            // scanner = new Scanner(new BufferedReader(new FileReader("directions.txt")));
            // Showing the advantage of using this method for reading big files
            //scanner = new Scanner(new BufferedReader(new FileReader("directions_big.txt")));
            //scanner.useDelimiter(",");

//            // While scanner still has something in it.
//            while (scanner.hasNextLine()){
//                // Skipping the delimiter (which has been set to a comma).
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String direction = scanner.next();
//                scanner.skip(scanner.delimiter());
//                // We're using 'nextLine' to tell the scanner to move on to the next line
//                String dest = scanner.nextLine();
//
//                // converting 'dest' to an integer because it will be needed as an integer
//                int destination = Integer.parseInt(dest);
//                // 'destination': JVM handles the unboxing of 'destination'
//                System.out.println(loc + ": " + direction + ": " + destination);
//
//                // Getting the value of each 'loc'
//                Location location = locations.get(loc);
//                location.addExit(direction, destination);
//            }


                                // OR


            // While scanner still has something in it.
//            while (scanner.hasNextLine()){
            while ((input = dirFile.readLine()) != null){
//                // Skipping the delimiter (which has been set to a comma).
//                int loc = scanner.nextInt();
//                scanner.skip(scanner.delimiter());
//                String direction = scanner.next();
//                scanner.skip(scanner.delimiter());
//                // We're using 'nextLine' to tell the scanner to move on to the next line
//                String dest = scanner.nextLine();
//
//                // converting 'dest' to an integer because it will be needed as an integer
//                int destination = Integer.parseInt(dest);

                            //OR

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

        /*
        finally {
            if (scanner != null){
                scanner.close();
            }
        }
        */


//        // Temporary 'exit' map
//        Map<String, Integer> tempExit = new HashMap<>();
//
//        // We're adding new locations. Keys 0,1,2..,5 gets converted to an Integer object (Autoboxing).
//        // Adding key-value pairs.
//        // Key = 0, Value = instance of Location class
//        //locations.put(0, new Location(0, "Exit", tempExit));
//        locations.put(0, new Location(0, "Exit", null));
//
//        // LOCATION 1
//        tempExit = new HashMap<>();
//        // Find and Replacing the original text like so:
//        // Edit > Find > Replace
//        // Type "locations.get\(\d\).addexit" in the top box
//        // Make sure 'Regex' (Regular Expression) is ticked.
//        // Type "tempExit.put" in the bottom box.
//        // Finally click 'Replace all'.
//        tempExit.put("W", 2);
//        // Check the screenshot
//        // We're mapping out each room.
//        // 'gets' the first item in the locations map
//        // These 4 items will allow us to move to the north, south, east and west directions.
////        tempExit.put("W", 2);
//        tempExit.put("E", 3);
//        tempExit.put("S", 4);
//        tempExit.put("N", 5);
//        locations.put(1, new Location(1, "Road",tempExit));
//
//
//        // LOCATION 2
//        // We're removing the exits as it's redundant code and then adding it to the constructor.
//        // The 5 'addExit' lines below are now replaced by one line in the constructor
//        // This is an indication that we're finishing the game
//        // tempExit.put("Q", 0);
//
//        tempExit = new HashMap<>();
//        // Mapping the rest of the rooms out.
//        tempExit.put("N", 5);
//        // tempExit.put("Q", 0);
//        locations.put(2, new Location(2, "Hill",tempExit));
//
//        // LOCATION 3
//        // We want to make sure we're setting tempExit to a new hashmap before each room so that the previous rooms exit are not included in that
//        // new room.
//        tempExit = new HashMap<>();
//        tempExit.put("W", 1);
//        // tempExit.put("Q", 0);
//        locations.put(3, new Location(3, "Building",tempExit));
//
//        // LOCATION 4
//        tempExit = new HashMap<>();
//        tempExit.put("N", 1);
//        tempExit.put("W", 2);
//        // tempExit.put("Q", 0);
//        locations.put(4, new Location(4, "Valley",tempExit));
//
//        // LOCATION 5
//        tempExit = new HashMap<>();
//        tempExit.put("S", 1);
//        tempExit.put("W", 2);
//        // tempExit.put("Q", 0);
//        locations.put(5, new Location(5, "Forest",tempExit));
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
