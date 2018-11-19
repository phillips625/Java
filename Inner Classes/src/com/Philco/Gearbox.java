package com.Philco;

import java.util.ArrayList;

/**
 * Created by PhillipsDaramola on 02/10/2017.
 */
                // Static nested classes
// Gearbox is the outer class.
public class Gearbox {

    // Stores all the gears objects (from our inner Gear class).
    private ArrayList<Gear> gears;
    private int maxGears;
           // Changing the name of a field.
    // Right click 'currentGear', Click Refactor, click 'Rename', rename the field, then press Enter.
    private int currentGear = 0;
    // Ability to operate the clutch.
    private boolean clutchIsIn;

    public Gearbox(int maxGears) {
        this.maxGears = maxGears;
        // To fix this error (error:  new ArrayList<>()) - Right click on the 'Inner Classes' file on the top left - above the 'idea' file.
        // Click 'Open Module Settings', Click Project, Set the 'Project Language Level' to the right version - Java 8 in this case. Click OK.
        this.gears = new ArrayList<>();
        // This is a new Gear - to be added to the arraylist above.
        Gear neutral = new Gear(0, 0.0);
        this.gears.add(neutral);

        // Adds gear to the gear box.
        for (int i = 0; i < maxGears; i++){
            addGear(i, i * 5.3);
        }
    }

    // Better to add this to the constructor.
    public void addGear(int number, double ratio){
        if ((number > 0) && (number <= maxGears)){
            this.gears.add(new Gear(number, ratio));
        }
    }

    public void operateClutch (boolean in){
        this.clutchIsIn = in;
    }

    public void changeGear(int newGear){
        if ((newGear >= 0) && (newGear < this.gears.size()) && this.clutchIsIn){
            this.currentGear = newGear;
            System.out.println("Gear " + newGear + " selected.");
        }
        else {
            System.out.println("Grind");
            this.currentGear = 0;
        }
    }

    public double wheelSpeed(int revs){
        if (clutchIsIn){
            System.out.println("Stay Calm..");
            return 0.0;
        }

        // 'gears.get(currentGear)' returns a gear object from an arraylist of gears.
        return revs * gears.get(currentGear).getRatio();
    }


    // Only the outer class (Gearbox) can have access to the Gear class.
    // Inner class - Gear. Inner classes are useful because having a stand-alone Gear class is not useful since the only class that needs it
    // is the Gearbox class. It doesn't make sense to talk about a gear except we're referring to a gearbox - hence our use of an inner Gear class.
    // The Gear class has access to the fields and functions of the Gearbox class, even those marked as private.
    private class Gear {

        private int gearNumber;
        private double ratio;

        // This class wouldn't normally be exposed to the public.
        public Gear(int gearNumber, double ratio) {
            // This currentGear refers to the currentGear in the Geat class, not the one in the Gearbox class.
            this.gearNumber = gearNumber;
            this.ratio = ratio;
        }

        public double getRatio() {
            return ratio;
        }

        public double driveSpeed(int revs){
            return revs * this.ratio;
        }
    }
}

























