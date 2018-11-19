package com.Philco;

/**
 * Created by PhillipsDaramola on 09/07/2017.
 */

// Outlander has the characteristics of the Vehicle and Car classes
public class Outlander extends Car {

    private int roadServiceMonths;

    public Outlander(int roadServiceMonths) {
        super("Outlander", "4WD", 5, 5, 6, false);
        this.roadServiceMonths = roadServiceMonths;
    }

    public void accelerate(int rate) {

        // getCurrentVelocity() is returned from the Vehicle class
        int newVelocity = getCurrentVelocity() + rate;

        if (newVelocity == 0) {
            stop();
            changeGear(1);
        }
        else if (newVelocity > 0 && newVelocity <=10 ){
            changeGear(1);
        }
        else if (newVelocity > 10 && newVelocity <=20 ){
            changeGear(2);
        }
        else if (newVelocity > 20 && newVelocity <=30 ){
            changeGear(3);
        }
        else {
            changeGear(4);
        }

        if (newVelocity > 0){
            changeVelocity(newVelocity, getCurrentDirection());
        }
    }
}
