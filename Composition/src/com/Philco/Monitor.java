package com.Philco;

/**
 * Created by PhillipsDaramola on 09/07/2017.
 */
public class Monitor {

    private String model;
    private String manufacturer;
    private int size;

    // This is COMPOSITION. The Resolution class is part of a monitor AKA Monitor HAS A resolution
    private Resolution nativeResolution;

    public Monitor(String model, String manufacturer, int size, Resolution nativeResolution) {
        this.model = model;
        this.manufacturer = manufacturer;
        this.size = size;
        this.nativeResolution = nativeResolution;
    }

    public void drawPixelAt(int x, int y, String color) {
        System.out.println("Drawing pixel at " + x + "," + y + " in color " + color);
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public int getSize() {
        return size;
    }

    public Resolution getNativeResolution() {
        return nativeResolution;
    }
}
