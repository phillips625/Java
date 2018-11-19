package com.Philco;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PhillipsDaramola on 01/10/2017.
 */
public class Monster implements ISaveable {

    private String name;
    private int hitPoints;
    private int strength;

    public Monster(String name, int hitPoints, int strength) {
        this.name = name;
        this.hitPoints = hitPoints;
        this.strength = strength;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public int getStrength() {
        return strength;
    }

    // 1. CMD + N
    // 2. Click 'Implement Methods'

    // The write and read methods can be different from that of the Player class.
    @Override
    public List<String> write() {
        // The means that we only have to change the ArrayList to other list types (like vector, etc) in one spot.
        ArrayList<String> values = new ArrayList<String>();

        // THE 4 FIELDS IN THIS CLASS ARE SAVED IN AN ARRAY LIST.

        // Specifying what index to add the values
        values.add(0, this.name);
        // this.hitPoints will be casted to be a string.
        values.add(1, "" + this.hitPoints);
        values.add(2, "" + this.strength);

        // Returns the list so it can be used elsewhere.
        return values;
    }

    @Override
    public void read(List<String> savedValues) {
        // savedValues.size() checks that there is at least one value in the list.
        if (savedValues != null && savedValues.size() > 0){
            // Don't have to do 'parseInt(savedValues.get(0))' because 'this.name' is already a 'string'.
            this.name = savedValues.get(0);
            // parseInt will make the number we get from savedValues.get(1) into a 'string'.
            this.hitPoints = Integer.parseInt(savedValues.get(1));
            this.strength = Integer.parseInt(savedValues.get(2));
        }
    }

    @Override
    public String toString() {
        return "Monster{" +
                "name='" + name + '\'' +
                ", hitPoints=" + hitPoints +
                ", strength=" + strength +
                '}';
    }
}
