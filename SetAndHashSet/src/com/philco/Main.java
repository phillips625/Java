package com.philco;

// Hash-Collection (Hashmap and Hashset) Hashcode determines determines which bucket objects go into.
// Objects that are equal have the same hashcode and they would ultimately end up in the same bucket.
// It is not mandatory though - 2 objects that are equal do not have to have the same hashcode.
// If two objects are equal, they MUST have the same hashcode. If two objects compare equal, they must have the same hashcode
// so that the new object is not added to the collection.



// 1. The main difference between set interface and other types of sets is that sets cannot contain any duplicates.
//    If you want to check that there are no duplications is your list, use a 'set' instead of a 'list'.

// 2. We cannot retrieve items in a 'set' as can in our 'list'- so you cannot say retrieve the 10th element in a set.

// 3. The keys in a set can be retrieved using the 'keyset' method.

// 4. The best performing implementation of the 'set' interface is the 'Hashset'. It uses 'hashes' to store the items.

// Google: compressed pointers.

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {

    // If you're using your own object as a key in a map or an element in a set (as shown below), the 'equals' and the 'hashcode'
    // methods should be overridden.

    // Key: String, Value: HeavenlyBody.
    //private static Map<String, HeavenlyBody> solarSystem = new HashMap<>();

    // Key: The Key inner class we created in the HeavenlyBody class
    private static Map<HeavenlyBody.Key, HeavenlyBody> solarSystem = new HashMap<>();
    // We could also have included Comet and Asteroid sets (as the solar system does not just contain planets).
    private static Set<HeavenlyBody> planets = new HashSet<>();

    public static void main(String[] args) {

                    // Mercury
        HeavenlyBody temp = new Planet("Mercury", 88);
        // Storing the key-value pair to our map.
        solarSystem.put(temp.getKey(), temp);
        // Storing the key-value pair to our set.
        planets.add(temp);

                    // Venus
        temp = new Planet("Venus", 225);
        // Storing the key-value pair to our map.
        solarSystem.put(temp.getKey(), temp);
        // Storing the key-value pair to our set.
        planets.add(temp);

                    // Earth
        temp = new Planet("Earth", 365);
        // Storing the key-value pair to our map.
        solarSystem.put(temp.getKey(), temp);
        // Storing the key-value pair to our set.
        planets.add(temp);

                    // Moon
        HeavenlyBody tempMoon = new Moon("Moon", 27);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        // Adding the moon to the earth
        temp.addSatellite(tempMoon);

                    // Mars
        temp = new Planet("Mars", 687);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        tempMoon = new Moon("Deimos", 1.3);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); // temp is still Mars

        tempMoon = new Moon("Phobos", 0.3);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); // temp is still Mars

                // Jupiter
        temp = new Planet("Jupiter", 4332);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        tempMoon = new Moon("Io", 1.8);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); // temp is still Jupiter

        tempMoon = new Moon("Europa", 3.5);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); // temp is still Jupiter

        tempMoon = new Moon("Ganymede", 7.1);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); // temp is still Jupiter

        tempMoon = new Moon("Callisto", 16.7);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSatellite(tempMoon); // temp is still Jupiter

                // Saturn
        temp = new Planet("Saturn", 10759);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

                // Uranus
        temp = new Planet("Uranus", 30660);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

                // Neptune
        temp = new Planet("Neptune", 165);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

                // Pluto
        temp = new Planet("Pluto", 248);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

                // Printing all the Planets
        System.out.println("Planets");
        for (HeavenlyBody planet : planets){
            System.out.println("    " + planet.getKey());
        }

                    // Printing all Jupiter
        // Getting 'Jupiter's' HeavenlyBody value from its 'Jupiter' (string) key.
        // get("Jupiter") returns the value of 'Jupiter', which is then assigned to 'body'.
        // HeavenlyBody body = solarSystem.get("Jupiter");

                        // OR
        // Key is now a Key class
        HeavenlyBody body = solarSystem.get(HeavenlyBody.makeKey("Mars", HeavenlyBody.BodyTypes.PLANET));
        System.out.println("Moon of " + body.getKey());
        for (HeavenlyBody jupiterMoon : body.getSatellites()){
            System.out.println("\t" + jupiterMoon.getKey());
        }

        Set<HeavenlyBody> moons = new HashSet<>();
        for (HeavenlyBody planets: planets){
        // addAll method creates a set union - i.e prints out all the moon in both planets (and also pick one moon if there is a duplicate)
            moons.addAll(planets.getSatellites());
        }

        System.out.println("All moons");
        // Gets all the moons in the planets
        for (HeavenlyBody moon: moons){
            System.out.println("\t" + moon.getKey());
        }

                // This is going to show the consequence of not using the HASHCODE and EQUALS methods.
        // Adding a different orbital period to pluto.

        // THIS WILL PRINT JUST FINE - AS PLUTO HERE IS NOW SEEN AS A 'DwarfPlanet'.
        HeavenlyBody pluto = new DwarfPlanet("Pluto", 352);

        // You'd think that we can only ever have one item in a set.
        // The reason why we get 2 plutos is because the two pluto objects do not compare 'equal'. If we had used them as keys in
        // a map, the same thing would have happened. This is why the 'equals' method has to be overridden in our 'HeavenlyBody' class.
        // In the default 'equals', ONLY if both reference point to the same object, then they are equal (hence the reason why we pluto
        // is printed twice - because java see that both references are pointing to different objects). Basically like saying java uses
        // '==' for the comparison.
        // E.g the String class has overridden the equals method (which is inherited from the base 'OBJECT' class)
        planets.add(pluto);

        for (HeavenlyBody planet : planets){
            // This will automatically invoke our toString method (which we overrode)
            System.out.println(planet);
      //      System.out.println(planet.getKey() + " : " + planet.getOrbitalPeriod());
        }

        HeavenlyBody earth1 = new Planet("Earth", 365);
        HeavenlyBody earth2 = new Planet("Earth", 365);

                    // In both cases, true should be printed.
        // Thus means that earth1 and earth2 are symmetric as they are both equal to each other.
        System.out.println(earth1.equals(earth2));
        System.out.println(earth2.equals(earth1));

                    // These two statements should print out false.
        System.out.println(earth1.equals(pluto));
        System.out.println(pluto.equals(earth1));


        solarSystem.put(pluto.getKey(), pluto);
        // This prints out the toString method in the HeavenlyBody class - as 'makeKey' returns a new HeavenlyBody object.
        System.out.println(solarSystem.get(HeavenlyBody.makeKey("Pluto", HeavenlyBody.BodyTypes.PLANET)));
        System.out.println(solarSystem.get(HeavenlyBody.makeKey("Pluto", HeavenlyBody.BodyTypes.DWARF_PLANET)));


                    // Print out the entire solar system map
        System.out.println();
        System.out.println("The solar system contains:");
        for (HeavenlyBody heavenlyBody : solarSystem.values()){
            // The toString method is invoked on each of the members of the 'solarSystem' map.
            System.out.println(heavenlyBody);
        }

//        // This is the base object that all other objects inherit from in Java - the object class.
//        Object o = new Object();
//        o.equals(o);
//        "pluto".equals("");
    }
}
