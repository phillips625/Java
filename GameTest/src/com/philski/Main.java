package com.philski;

// These 3 CLASSES WERE AUTOMATICALLY FOUND FOR US BY IntelliJ (where we imported them in the External libraries section)
import com.example.game.ISaveable;
import com.example.game.Monster;
import com.example.game.Player;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Player tim = new Player("Jam", 4, 5);
        System.out.println(tim.toString());

        saveObject(tim);
        // Change the 'hitPoints'.
        tim.setHitPoints(6);
        // 'tim' in 'sout' is assumed to be 'tim.toString()' because you haven't specified otherwise.
        System.out.println(tim);

        tim.setWeapon("StormBlazer");
        saveObject(tim);
        //loadObject(tim);
        System.out.println(tim);

        // THIS IS A PREFERABLE MAY OF DECLARING A CLASS IMPLEMENT FROM AN INTERFACE - BETTER TO KEEP THINGS GENERIC. YOU CAN ALWAYS
        // CAST THE OBJECT AS WHEN YOU NEED A SPECIFIC FUNCTIONALITY IN A CLASS e.g '((Monster) woofwoof).getStrength()'.

        // Declaring the instance as the ISaveable interface.
        // YOU 'Declaring the instance as the interface' WHEN YOU WANT THE OBJECT TO HOLD DIFFERENT TYPES, THEN USE THE INTERFACE
        // IF YOU WANT TO USE THE METHODS IN A PARTICULAR CLASS, THEN DECLARE THE OBJECT AS AN INSTANCE OF THE CLASS.
        ISaveable woofwoof = new Monster("Werewolf", 23, 45);
        // This wouldn't work because the base class is ISaveable - aka we declare the interface. Only works with 'Monster woofwoof'.
        // woofwoof.getStrength();
                // SOLUTION
        // Cast the woofwoof object.
        System.out.println("Strength = " + ((Monster) woofwoof).getStrength());
        //System.out.println(woofwoof);
        saveObject(woofwoof);
    }

    // Returns an array list of string that you input into the terminal (from the keyboard).
    public static ArrayList<String> readValues(){

        ArrayList<String> values = new ArrayList<String>();

        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        int index = 0;

        System.out.println("Choose \n" +
                    "1 to enter a string \n" +
                    "0 to quit");

        while(!quit){

            System.out.println("Choose an option");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 0:
                    quit = true;
                    break;

                case 1:
                    System.out.println("Enter a string:");
                    String stringInput = scanner.nextLine();
                    //Adding the stringInput to the array list.
                    values.add(index, stringInput);
                    index++;
                    break;
            }
        }

        return values;
    }

    // objectToSave allows us to use any class that has implemented the 'ISaveable' interface.
    public static void saveObject(ISaveable objectToSave){

        // 'objectToSave.write()' returns a list of strings.
        for (int i = 0; i < objectToSave.write().size(); i++){
            // objectToSave.write().get(i) accesses the items in the array list returned by 'objectToSave.write()'.
            System.out.println("Saving " + objectToSave.write().get(i) + " to storage device.");
        }
    }

    // This allows us to load the data back.
    public static void loadObject(ISaveable objectToLoad){
        ArrayList<String> values = readValues();
        // 'read' store the value to the 'Player' fields.
        objectToLoad.read(values);
    }
}




























