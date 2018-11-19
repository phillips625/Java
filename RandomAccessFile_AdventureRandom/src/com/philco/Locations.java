package com.philco;

import java.io.*;
import java.util.*;

/**
 * Created by PhillipsDaramola on 20/11/2017.
 */


public class Locations implements Map<Integer, Location> {

    // Stores the locations
    private static Map<Integer, Location> locations = new LinkedHashMap<>();
    // Given we're using a list of rooms that are linked in some way, we're going to be using the LinkedHashMap.
    private static Map<Integer, IndexRecord> index = new LinkedHashMap<>();

    private static RandomAccessFile ra;

    // 'throws IOException' meaning no need to implement our finally and catch.
    // We don't have to set up 'finally' as try-with-resources will automatically close our files.
    public static void main(String[] args) throws IOException{

                            // WRITES FROM MEMORY INTO FILE.
        // The output file (locations.dat) will have a Binary format.
        // Chosen 'locations.dat' as it contains binary data.
        // 'FileOutputStream' actually opens the file.
        // DataOutputStream contains method write all primitive types: in this case, we have used writeInt to write integers in a binary format.
        // Although Strings are not primitive types, DataOutputStream contains a method, writeUTF, to write Strings.
//        try (DataOutputStream locFile = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("locations.dat")))){
//            for (Location location : locations.values()){
//
//                // Since the first values in the locations_big.txt file is an integer, we're writing an integer first.
//                locFile.writeInt(location.getLocationID());
//                // writeUTF is used to write a String.
//                locFile.writeUTF(location.getDescription());
//                System.out.println("Writing location " + location.getLocationID() + " : " + location.getDescription());
//                // Size of exits for each particular location.
//                System.out.println("Writing " + (location.getExits().size() - 1) + " exits.");
//                // Writing the size of the exits into file.
//                locFile.write(location.getExits().size() - 1);
//                // Going through the list of keys.
//                for (String direction: location.getExits().keySet()){
//
//                    // If we're not writing the quite location
//                    if (!direction.equalsIgnoreCase("Q")){
//                        // Couple of tabs.
//                        System.out.println("\t\t" + direction + "," + location.getExits().get(direction));
//                        locFile.writeUTF(direction);
//                        locFile.writeInt(location.getExits().get(direction));
//                    }
//
//                }
//            }
//        }

                    // SERIALIZABLE.

        // Writing to the 'Locations_rand.dat' file.
        // 'rwd' indicates that we want to open the file for reading and writing. It also indicates that we want write to occur
        // synchronously. This is good practice if you have multiple threads reading the file (in the example, we only have one thread).
        // 'RandomAccessFile' class is handling the synchronization.
        // One disadvantage of using RandomAccessFile is that we can't read and write objects - RandomAccessFile does not contain
        // readObject and writeObject methods.
        // Notice that RandomAccessFile does not chain the BufferedWriter class. That is because the RandomAccessFile writes to file randomly
        // while BufferedWriter writes to file in BATCHES.

        // AIM: LOAD DATA FROM A RANDOM ACCESS FILE - AS OPPOSED TO FROM MEMORY.
        // IF WE'RE ACCESSING THE FILE SEQUENTIALLY, WE DO NOT NEED ANY POINTERS, BUT BECAUSE WE'RE ACCESSING THE FILE IN A RANDOM
        // FASHION, WE HAVE TO MANUALLY MOVE THE POINTER OURSELVES.
        try (RandomAccessFile rao = new RandomAccessFile("Locations_rand.dat", "rwd")){
            // Since the file's pointed starts at 0, we can then use the writeInt method.
            rao.writeInt(locations.size());
            // Each index record contains 3 integers - the Location id, the offset for the record, the length of the Location record
            // (hence the reason why we're multiplying by 3).
            // indexSize is calculated by multiplying the number of locations by the number of ints contained in each record (3) and
            // then by the number of bytes in an integer.
            int indexSize = locations.size() * 3 * Integer.BYTES;
            // Calculate the currrent position of the file pointer. We're accounting for the integer we're about to write to the file by
            // adding 'Integer.BYTES. This will give us the offset for the Locations section. We're casting to an integer because 'getFilePointer'
            // returns a long.
            int locationStart = (int) (indexSize + rao.getFilePointer() + Integer.BYTES);
            rao.writeInt(locationStart);

            // The next section is the index. Before we can write the index, you have to write the locations and that is because each each index
            // record requires the offset for the location and ofcourse we don't know the offset until we have written the location. Since disk access
            // is expensive (going from writing 'index' then 'location', then index then location, etc can be expensive in terms of
            // memory access), we're are going to be writing the location as a whole and then the index as a whole. To do this, we have to
            // build the index in memory as we write the locations. Because we want to jump back to the file once we finish writing the
            // locations, we'll save the current position of the file pointer, so we can jump back to it when we want to write the index (since
            // we'll write the index after the two integers we have already written, we'll be writing it to offset 8 - which is where the file
            // pointer is currently positioned after writing the number of locations in the location's section offset).
            long indexStart = rao.getFilePointer();

            // At this point, we're ready to write the locations.
            // Setting the offset for the first Location to 'startPointer'. We need to use this to calculate the Location's record
            // length after we have written it to the file.
            int startPointer = locationStart;
            // Move the file point to the first Location offset. We only have to do this for the first Location - because after that
            // we'll write all the data sequentially.
            rao.seek(startPointer);

            for (Location location : locations.values()){
                rao.writeInt(location.getLocationID());
                rao.writeUTF(location.getDescription());

                // Exits for each Location.
                StringBuilder builder = new StringBuilder();
                for (String direction : location.getExits().keySet()){
                    if (!direction.equalsIgnoreCase("Q")){
                        builder.append(direction);
                        builder.append(",");
                        // 'get' gets the Integer values of each String key.
                        builder.append(location.getExits().get(direction));
                        // Ideally, we want to remove this comma for the last exit.
                        builder.append(",");

                        // WHAT WE'LL END UP WITH AFTER StringBuilder
                        //                       'direction, locationID' corresponds to that of the 'exits'.
                        // direction, locationID, direction, locationID
                        // N, 1, U, 2
                    }
                }

                // The exits are written to the file here.
                rao.writeUTF(builder.toString());

                // 'rao.getFilePointer() - startPointer' - Record's length is the current position of the start point minus tha startPointer

                IndexRecord record = new IndexRecord(startPointer, (int) (rao.getFilePointer() - startPointer));
                index.put(location.getLocationID(), record);

                // Updating the startPointer for the next Location.
                startPointer = (int) rao.getFilePointer();
            }

            // At this point we've now written all the Locations to the file, we can now write our index as we now have the full lists
            // of indexes available.

            // Getting the pointer - so we can continue to write to the file.
            rao.seek(indexStart);

            // Going through the 'index' record.
            for (Integer locationID : index.keySet()){
                rao.writeInt(locationID);
                rao.writeInt(index.get(locationID).getStartByte());
                rao.writeInt(index.get(locationID).getLength());
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

                            // RandomAccessFile class
    // 1. The first four bytes will contain the number of locations (bytes 0 - 3)
    // 2. The next four bytes will contain the start offset of the locations section (bytes 4 - 7)
    // 3. The next section of the file will contain the index (the index is 1692 bytes long. It will start at byte 8 and end at byte 1699)
    // 4. The final section of the file will contain the location records (the data). It will start at byte 1700
    static {

        try {

            // We're opening the file here.
            ra = new RandomAccessFile("locations_rand.dat", "rwd");
            // This reads the number of locations.
            // It is good practise to write the number of records at the start of a file that is being accessed in a random fashion.
            int numLocations = ra.readInt();
            // This reads the offset of the location section.
            long locationStartPoint = ra.readInt();

            // To read the index, we're looping through until the file pointer reaches the locations offset.
            // We're reading the index and creating the locations record as we go.
            while (ra.getFilePointer() < locationStartPoint){
                int locationId = ra.readInt();
                int locationStart = ra.readInt();
                int locationLength = ra.readInt();

                IndexRecord record = new IndexRecord(locationStart, locationLength);
                index.put(locationId, record);
            }
        }

        catch (IOException e){
            System.out.println("IOException in SIB: " + e.getMessage());
        }

        // This outer try-catch block catches any other exceptions. In a real world application, we'll log the exceptions in a file.
        // You want to know the format in which the characters are arranged (in the original file) so that you can
        // read in the binary data.
//        try (ObjectInputStream locFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream("locations.dat")))){
//            // eof = End of file.
//            boolean eof = false;
//            while (!eof){
//                try {
//                    // 'readObject' takes care of reading all the fields and exits in each Location object.
//                    // 'readObject' method returns a Location object - hence the reason for casting.
//                    Location location = (Location) locFile.readObject();
//                    System.out.println("Read location " + location.getLocationID() + " : " + location.getDescription());
//                    System.out.println("Found " + location.getExits().size() + " exits");
//                    locations.put(location.getLocationID(), location);
//                }
//                catch (EOFException e){
//                    eof = true;
//                }
//            }
//
//        }
//
//        catch (InvalidClassException e){
//            System.out.println("InvalidClassException: " + e.getMessage());
//        }
//
//        catch(IOException io) {
//            System.out.println("IO Exception: " + io.getMessage());
//        }
//
//        // This will be thrown when the runtime tries to read a class that is not of the 'Location' type in locFile.
//        catch (ClassNotFoundException e) {
//            System.out.println("Class not found exception: " + e.getMessage());
//        }




//            while (!eof){
//                // Nested try block - this try-catch block catches any end of file exceptions.
//                try {
//                    Map<String, Integer> exits = new LinkedHashMap<>();
//                    // Read in the integer ID in the locations.dat file.
//                    int locID = locFile.readInt();
//                    // Read in the String description in the locations.dat file.
//                    String description = locFile.readUTF();
//                    // Read in the integer 'number of exits' in the locations.dat file.
//                    int numExits = locFile.readInt();
//                    System.out.println("Read Location " + locID + " : " + description);
//                    System.out.println("Found " + numExits + " exits.");
//
//                    for (int i = 0; i < numExits; i++){
//                        String direction = locFile.readUTF();
//                        int destination = locFile.readInt();
//                        exits.put(direction, destination);
//                        System.out.println("\t\t" + direction + "," + destination);
//                    }
//
//                    locations.put(locID, new Location(locID, description, exits));
//                }
//
//                // EOFException - End Of File exception. This will be triggered when we reach the end of the input from the 'locations.dat'
//                // file.
//                catch (EOFException e){
//                    eof = true;
//                }
//
//            }

        // Because we're not throwing an exception, we have to catch an exception.
//        catch(IOException e){
//            System.out.println("IO Exceptions");
//        }

    }

            // Closes file after loading a location destination from the Locations_rand.dat file
    // The reason we're throwing IOException is to let all the exceptions bubble up to the operating system - meaning that he application is
            // going to exit and the exception will be written to the console.
    public void close() throws IOException{
        // Closes the Random access file (Locations_rand.dat) when the player quits the game.
        ra.close();
    }

    // This is going to be used when a player moves to the next Location.
    // The method grabs the location from the random access file, builds up the descriptions and the exits, creates a location object
    // and add the various exits to the location and then returns the location.
    public Location getLocation (int locationId) throws IOException{

        IndexRecord record = index.get(locationId);
        // We're now accessing the random access file
        // The code below will point to the correct offset - this will allow us to get access to this location ( from the random
        // access file). We're moving the startByte to the start of the Location offset.
        ra.seek(record.getStartByte());
        // Now we can read the locationID of this particular location.
        int id = ra.readInt();
        // Now we want to read the description of the room.
        String description = ra.readUTF();

        // Read exits.
        String exits = ra.readUTF();

        // Now we want to extract the exits - which have commas between them.
        // We're going to be using a String array. We're getting the various exits from this particular location and storing them in
        // a string array.
        // String[] exitPath = new String(exits).split(",");
                        // OR
        String[] exitPath = exits.split(",");

        // Passing null is still initializing the 'exits' HashMap in the Location class.
        Location location = new Location(locationId, description, null);

        // In this if statement, we're adding the exits to the Location.
        // If locationId = 0, then it means that the game will be quit.
        if (locationId != 0){
            // We're going through all the exits that we find and then add them one at a time to a Location.
            for (int i = 0; i < exitPath.length; i++){
                System.out.println("exitPath = " + exitPath[i]);
                System.out.println("exitPath [+1] = " + exitPath[i+1]);

                // Grabbing the direction for the location.
                String direction = exitPath[i];
                // exits is the actual direction and then the locationID where that direction does to.
                int destination = Integer.parseInt(exitPath[++i]);
                location.addExit(direction, destination);
            }
        }

        return location;
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
