package com.philco;

import sun.awt.Mutex;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by PhillipsDaramola on 24/10/2017.
 */
public class LightSwitch extends Thread {

    private boolean runStatus;
    private int lightCount;
    private ArrayList<Light> lights;
    private ArrayList<Colour> colours;

    public LightSwitch(int lightCount, ArrayList<Colour> colours) {
        this.lightCount = lightCount;
        this.colours = colours;
    }

    public boolean getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(boolean runStatus) {
        this.runStatus = runStatus;
    }

    public void run() {
        Mutex mutex = new Mutex();
        implementColours();
        runStatus = true;
        while (runStatus) {
            for (Light light : lights) {
                try {
                    mutex.lock();
                    if (!runStatus) {
                        mutex.unlock();
                        this.join();
                        break;
                    }
                    turnOnLightForOneSecond(light);
                    mutex.unlock();
                } catch (Exception e) {
                    UI.PrintMessage("LIGHT SWITCH ERROR: " + e);
                }
            }
        }
    }


    private void turnOnLightForOneSecond(Light currentLight) throws InterruptedException {
        currentLight.turnOn();
        printLightStatus(currentLight);
        Thread.sleep(1000);
        currentLight.turnOff();
        printLightStatus(currentLight);
    }


    private void printLightStatus(Light currentLight) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        UI.PrintMessage(sdf.format(date) + " " + currentLight.getColour().getColourName() + " Light: " + currentLight.getLightStatus());
    }

    public ArrayList<Colour> getColours() {
        return colours;
    }

    public void setColours(ArrayList<Colour> colours) {
        this.colours = colours;
        implementColours();
    }

    private void implementColours() {
        lights = new ArrayList<Light>();
        int colourIndex = 0;
        for (int i = 0; i < lightCount; i++) {
            if (colourIndex >= colours.size()) {
                colourIndex = 0;
            }
            Light newLight = new Light(colours.get(colourIndex));
            lights.add(newLight);
            colourIndex++;
        }
    }

    public int getLightCount() {
        return lightCount;
    }

}

