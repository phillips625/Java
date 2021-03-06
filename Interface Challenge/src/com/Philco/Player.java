package com.Philco;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhillipsDaramola on 29/09/2017.
 */
public class Player implements ISaveable {

    private String name;
    private int hitPoints;
    private int strength;
    private String weapon;

    // Hard coding the weapon.

    public Player(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
        this.weapon = "Sword";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public String getWeapon() {
        return weapon;
    }

    public void setWeapon(String weapon) {
        this.weapon = weapon;
    }

    // Overriding the toString method


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                ", weapon='" + weapon + '\'' +
                '}';
    }

            // write AND read functions can be used in game development.
    // Stub methods are methods that are empty.
    @Override
    public List<String> write() {

        // The means that we only have to change the ArrayList to other list types (like vector, etc) in one spot.
        List<String> values = new ArrayList<String>();

        // THE 4 FIELDS IN THIS CLASS ARE SAVED IN AN ARRAY LIST.

        // Specifying what index to add the values
        values.add(0, this.name);
        // this.hitPoints will be casted to be a string.
        values.add(1, "" + this.hitPoints);
        values.add(2, "" + this.strength);
        values.add(3, this.weapon);

        // Returns the list so it can be used elsewhere.
        return values;
    }

    // This checks that the received list is a valid list.
    // Even though we're passing a list, 'savedValues' is an array list and we can still use the methods associated with an array list.
    @Override
    public void read(List<String> savedValues) {

        // savedValues.size() checks that there is at least one value in the list.
        if (savedValues != null && savedValues.size() > 0){
            // Don't have to do 'parseInt(savedValues.get(0))' because 'this.name' is already a 'string'.
            this.name = savedValues.get(0);
            // parseInt will make the number we get from savedValues.get(1) into a 'string'.
            this.hitPoints = Integer.parseInt(savedValues.get(1));
            this.strength = Integer.parseInt(savedValues.get(2));;
            this.weapon = savedValues.get(3);
        }
    }
}
