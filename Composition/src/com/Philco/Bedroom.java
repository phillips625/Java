package com.Philco;

/**
 * Created by PhillipsDaramola on 16/07/2017.
 */
public class Bedroom {

    private String name;
    private Wall Wall1;
    private Wall Wall2;
    private Wall Wall3;
    private Wall Wall4;
    private Ceiling ceiling;
    private Bed bed;
    private Lamp lamp;

    public Bedroom(String name, Wall wall1, Wall wall2, Wall wall3, Wall wall4, Ceiling ceiling, Bed bed, Lamp lamp) {
        this.name = name;
        Wall1 = wall1;
        Wall2 = wall2;
        Wall3 = wall3;
        Wall4 = wall4;
        this.ceiling = ceiling;
        this.bed = bed;
        this.lamp = lamp;
    }

    public Lamp getLamp() {
        return this.lamp;
    }

    public void makeBed(){
        System.out.println("Bedroom -> making bed");
        bed.make();
    }
}
