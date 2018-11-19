package com.example.awtSample;

// '*' means to import all the classes, interfaces and static objects from the 'java.awt' package.
import java.awt.*;
// You'd think that the all-encompassing '*' will cover the two imports below, but it doesn't because the packages below are
// different - the package used here is 'java.awt.event'.
// import java.awt.event.*
            // OR
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by PhillipsDaramola on 10/10/2017.
 */
public class MyWindow extends Frame {

    //public MyWindow(String title) throws HeadlessException {
    public MyWindow(String title) {
        super(title);
        setSize(500, 140);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                // This is being overriden.
                // What we're saying is when we click the close button of the window, this will override the default code
                // with our code - System.exit(0);. Application closes doesn't happen by default.
                // super.windowClosed(e);
                System.exit(0);
            }
        });
    }

    // This method is used to draw on the screen.
    // 'g' is a graphics object.
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Font sansSerifLarge = new Font("SansSerif",Font.BOLD, 18);
        Font sansSerifSmall = new Font("SansSerif",Font.BOLD, 12);
        g.setFont(sansSerifLarge);
        g.drawString("The Complete Java Course", 60, 60);
        g.setFont(sansSerifSmall);
        g.drawString("by Big P", 60, 100);
    }
}
