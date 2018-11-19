package com.Philco;

/**
 * Created by PhillipsDaramola on 09/07/2017.
 */
public class PC {

    // Here we're saying the PC is comprised of the Case, Monitor and Motherboard classes. Each instance of PC that we create will have
    // Case, Monitor and Motherboard instances.
    // COMPOSITION: A PC HAS GOT a case, a monitor and a motherboard.
    // A Case isn't a computer.  A Motherboard isn't a computer.  A Monitor isn't a computer. They are part of a computer. Hence we make use of COMPOSITION,
    // instead of INHERITANCE.
    // This is composition as we have a PC class managing other objects.
    private Case theCase;
    private Monitor monitor;
    private Motherboard motherboard;

    public PC(Case theCase, Monitor monitor, Motherboard motherboard) {
        this.theCase = theCase;
        this.monitor = monitor;
        this.motherboard = motherboard;
    }

    public void powerUp() {
        // We're accessing the functions directly
        theCase.pressPowerButton();
        drawLogo();
    }

    private void drawLogo() {
        // Fancy graphics
        monitor.drawPixelAt(1200, 50, "yellow");
    }

}
