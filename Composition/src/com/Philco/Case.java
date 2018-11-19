package com.Philco;

/**
 * Created by PhillipsDaramola on 09/07/2017.
 */
public class Case {

    private String model;
    private String manufacturer;
    private String powerSupply;

    // Composition: This is a Dimensions object that is part of case.
    private Dimensions dimensions;

    public Case(String model, String manufacturer, String powerSupply, Dimensions dimensions) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.powerSupply = powerSupply;
        this.dimensions = dimensions;
    }

    public void pressPowerButton () {
        System.out.println("Power Button Pressed");
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }
}
