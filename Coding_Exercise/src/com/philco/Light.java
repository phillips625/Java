package com.philco;

/**
 * Created by PhillipsDaramola on 24/10/2017.
 */
public class Light {

    private boolean status = false;
    private Colour colour = null;
    private int brightness = 100;

    public Light(Colour colour){
        this.colour = colour;
    }

    public void setColour(Colour newColour){
        colour = newColour;
    }

    public Colour getColour(){
        return colour;
    }

    public void turnOn(){
        status = true;
    }

    public void turnOff(){
        status = false;
    }

    public String getLightStatus(){
        if(status){
            return "ON";
        }
        else{
            return "OFF";
        }
    }

    public int getBrightness() {
        return brightness;
    }

    public void increaseBrightness() {
        this.brightness = brightness + 10;
    }

    public void decreaseBrightness() {
        this.brightness = brightness - 10;
    }
}

