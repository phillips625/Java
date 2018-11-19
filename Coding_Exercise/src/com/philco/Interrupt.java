package com.philco;

import sun.awt.Mutex;

import java.util.Scanner;

/**
 * Created by PhillipsDaramola on 24/10/2017.
 */
public class Interrupt extends Thread {

    private LightSwitch lightSwitch;
    private Scanner scanner;

    public Interrupt(LightSwitch lightSwitch, Scanner scanner) {
        this.lightSwitch = lightSwitch;
        this.scanner = scanner;
    }

    public void run() {
        Mutex mutex = new Mutex();
        try {
            mutex.lock();
            if(scanner.hasNext()){
                if(scanner.next().equals("j")){
                    lightSwitch.setRunStatus(false);
                    UI.PrintMessage("Light Show Interrupted");
                    this.join();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mutex.unlock();
    }
}
