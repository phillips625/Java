package com.philco;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by PhillipsDaramola on 23/10/2017.
 */

// Final - cannot be changed
// Static - only one copy exists in memory.

// HeavenlyBody cannot be subclassed.
//public final class HeavenlyBody {

    // This class cannot be INSTANTIATED!
public abstract class HeavenlyBody {

    // name and bodyType can now be replaced by the Key object.
    // private final String name;
    // private final BodyTypes bodyType;

                    // OR
    private final Key key;

    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites;
    // Now our bodyType is of type BodyTypes - thanks to the enum.


    // ENUM - This is used by Java to group constants together.
    // Advantages of using ENUMS -
    // 1. Enums are types - which means that when we changed the constructor, we can make sure it only accepts a valid body type.
    // 2. We get an error at compile time (as opposed to run time) if the body type is not valid.
    // Think about enums as a mini class.
    public enum BodyTypes {
        // This enum will only accept one of these types specified.
        STAR,
        PLANET,
        DWARF_PLANET,
        MOON,
        COMET,
        ASTERIOD
    }
                    // OR

    // Unique number for each of the various body types.
//    public static final int STAR = 1;
//    public static final int PLANET = 2;
//    public static final int DWARF_PLANET = 3;
//    public static final int MOON = 4;
//    public static final int COMET = 5;
//    public static final int ASTERIOD = 6;

    public HeavenlyBody(String name, double orbitalPeriod, BodyTypes bodyType) {
            // Replaced by the Key class
//        this.name = name;
//        this.bodyType = bodyType;

                // OR
        this.key = new Key(name, bodyType);

        this.orbitalPeriod = orbitalPeriod;
        this.satellites = new HashSet<>();

    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Key getKey() {
        return key;
    }

    // This will be used to add a moon.
    public boolean addSatellite(HeavenlyBody moon){

        return this.satellites.add(moon);
    }

    public Set<HeavenlyBody> getSatellites() {
        // This is for security purposes. We don't want anyone messing with our own copy.
        // We're creating a new hashset of the current contents of our satellite.
        return new HashSet<>(this.satellites);
    }

//    @Override
//    public boolean equals(HeavenlyBody obj) {

            // BOTH THE EQUALS AND HASHCODE METHODS HAVE TO BE OVERRIDDEN!

    // Because the equals and hashcode methods have been implemented correctly, we get no duplicates (e.g. no the pluto duplicate
    // in the main class).
                // The parameter has to be an Object object to make this override valid.
        @Override
        public final boolean equals(Object obj) {
        // We are checking to see if we're dealing with the same object.
            // Checks if it's checking for itself.
        if (this == obj){
            return true;
        }

        // If the Heavenlybody class (we're currently in) doesn't match the class of 'obj', then return false.
            // In order to make sure that subclasses do not compare equal, we are checking the class of the actual object (obj.getClass) against the
            // the class of the object the method is in (this.getClass().
            // Won't return true if we're comparing to the subclass of itself - Heavenlybody cannot compare to a subclass of itself.
            // This check is unnecessary as this class is final - hence cannot be subclassed.
//        if ((obj == null) || (obj.getClass() != this.getClass())){
//            return false;
//        }
                            //OR
            // Checking that we're receiving the right type of class
            // We're using 'instanceof' because we're going to subclass the 'HeavenlyBody' class to create the planet, moon, etc classes.
            if (obj instanceof HeavenlyBody){
                HeavenlyBody theObject = (HeavenlyBody) obj;

                            // DRY principle - since we have an equivalent equals and hashcode method in the Key class, we can
                            // remove some of these code.

//                if (this.name.equals(theObject.getName())){
//                    // Returns true if these are true.
//                    return this.bodyType == theObject.getBodyType();
//                }

                                        // OR
                return this.key.equals(theObject.getKey());
            }


            // This means that the name is different or the body type is different.
            return false;

        // Given the String method has an 'equals' method, we cast the object we're comparing to to the HeavenlyBody,
            // we then use the String object to compare its name to the name of the current object (this.name).
//        String objName = ((HeavenlyBody) obj).getName();
//        return this.name.equals(objName);
    }

    /*
                    We're implementing the hashcode to prevent the same key-value pairs from being displayed - Pluto : 352.0 and
                    Pluto : 248.0 are in different buckets (because they both have different hashcodes), hence the reason why they
                    are both displayed as opposed to one being displayed.
    Pluto : 352.0
Venus : 225.0
Mercury : 88.0
Saturn : 10759.0
Pluto : 248.0
     */

    @Override
    public final int hashCode() {


        // Because the String class has implemented the hashCode function, we can use this function to generate the hashCodes as long
        // as they two things we're trying to compare are Strings (this solution wouldn't work if we're trying to compare an Heavenbody object
        // with another).
        //return this.name.hashCode();

        // We're adding a number small enough not to cause any problems. This guarantees that we're returning a number greater than ZERO
        // which our hashCode method could be returning.
        // As, enums have hashcodes, to create a unique hashcode, we'll add 'this.bodyType.hashCode'.

        // DRY principle - since we have an equivalent equals and hashcode method in the Key class, we can
        // remove some of these code.

        // return this.name.hashCode() + 57 + this.bodyType.hashCode();

                    // OR
        return this.key.hashCode();
    }

    // Used to make a key to do look ups in the map object.
    public static Key makeKey(String name, BodyTypes bodyType){
        return new Key(name, bodyType);
    }

    // BOTH THE NECESSARY HASHCODE AND EQUALS METHOD ARE CALLED. NOW PLUTO WOULD BE ADDED TO THE LIST ONCE. ONLY THE FIRST
    // PLUTO IS ADDED!


    // This toString method is mainly used for debugging!

    @Override
    public String toString() {
        return this.key.name + ": " + this.key.bodyTypes + ", " + this.orbitalPeriod;
    }

    // This class gives us a way to deal with 'name' and 'bodytype' as a single object.

    public static final class Key{

        private String name;
        private BodyTypes bodyTypes;

        // This constructor can still be accessed by the HeavenlyBody class
        private Key(String name, BodyTypes bodyTypes) {
            this.name = name;
            this.bodyTypes = bodyTypes;
        }

        public String getName() {
            return name;
        }

        public BodyTypes getBodyTypes() {
            return bodyTypes;
        }

        // Becuase we'll be using the Key class in a map, this is why we has to override the hashcode and equals method.
        @Override
        public int hashCode() {
            return this.name.hashCode() + 57 + this.bodyTypes.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            Key key = (Key) obj;

            // If condition checks that the name of the two object matches.
            if (this.name.equals(key.getName())){
                return (this.bodyTypes == key.getBodyTypes());
            }

            return false;
        }

        @Override
        public String toString() {
            return this.name + ": " + this.bodyTypes;
        }
    }

























}
