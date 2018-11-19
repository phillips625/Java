package com.Philco;

////// INHERITANCE: Car is a Vehicle; hence Car inherits from Vehicle
///// COMPOSITION: PC has a monitor, a motherboard, etc


/////// Since with Inheritance, you can only inherit from one class at a time, Composition fixes that issue.
// With Composition, you can inherit from multiple classes at the same time.

public class Main {

    public static void main(String[] args) {

        Dimensions dimensions = new Dimensions(20, 20, 5);
        Case theCase = new Case("220B", "Dell", "240", dimensions);

        /*
             Resolution nativeResolution = new Resolution(2,3);
             Monitor theMonitor = new Monitor("27 Inch", "Acer", 27, nativeResolution);
        */
                    // OR
        Monitor theMonitor = new Monitor("27 Inch", "Acer", 27, new Resolution(2540, 1540));

        Motherboard theMotherBoard = new Motherboard("BJ-200", "Asus", 4, 6, "v2.44");

        ///// Passing instances of the Case, Monitor and Motherboard classes
        /// Now it's time to put our PC together
        PC thePC = new PC(theCase, theMonitor, theMotherBoard);
        thePC.powerUp();



        /////////////////////// CHALLENGE /////////////////////////////////////////

        //// Setting the base objects
        Wall wall1 = new Wall("North");
        Wall wall2 = new Wall("South");
        Wall wall3 = new Wall("West");
        Wall wall4 = new Wall("East");

        Ceiling ceiling = new Ceiling(12, 55);
        Bed bed = new Bed("Modern", 4, 3, 2, 1);
        Lamp lamp = new Lamp("Classic", false, 75);

        Bedroom bedroom = new Bedroom("Phil", wall1, wall2, wall3, wall4, ceiling, bed, lamp);

        bedroom.makeBed();

        bedroom.getLamp().turnOn();
    }
}




















        /*
        // Accessing the drawPixelAt method in the Monitor object (that is returned from the PC class using the getMonitor() getter) and then accessing the drawPixelAt
        // method from that object
        thePC.getMonitor().drawPixelAt(2,3, "red");

        thePC.getMotherboard().loadProgram("Windows 1.0");

        thePC.getTheCase().pressPowerButton();
        */