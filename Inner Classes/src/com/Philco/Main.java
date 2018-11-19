package com.Philco;

            // Inner class - nesting a class inside another class.
            // 3 Types of nested classes - static nested classes - used to associate a class with its outer class (cannot access its non-static
//          // methods or members of its outer class without first creating the instance of that class), non-static nested class aka inner class - we use the local class
            // that is defined inside a scope block it is usually a method, anonymous class - nested class without a class name.

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Button btnPrint = new Button("Print");

    public static void main(String[] args) {

        // This is a local class.
        // Less useful than an anonymous class - think eventlisteners (every
        // button has a different event triggered when they are clicked).
        // Only useful for this block of code (main method). This is useful if you want to assign
        // the same object/functionality to several buttons - say you have several buttons on the screen at the
        // same time.
        // Local class attached to the main method that implements the interface in the button class.

//        class ClickListener implements Button.OnClickListener{
//
//            // Automatically runs when you run the program.
//            public ClickListener(){
//                System.out.println("I've been attached");
//            }
//
//            @Override
//            public void onClick(String title) {
//                System.out.println(title + " was clicked.");
//            }
//        }
//        // This is a useful technique for designing games.
//        btnPrint.setOnClickListener(new ClickListener());

                // Anonymous function - think event listeners.
        // Type new + 'letter O' (Intellij automatically creates the anonymous function).
        // New 'new Button.OnClickListener()' object is NAMELESS.
        btnPrint.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(String title) {
                System.out.println(title + " was clicked.");
            }
        });
        listen();

                // For static nested classes.
//        Gearbox mcLaren = new Gearbox(6);
//        // 'addGear' has now been added to the constructor.
//        // mcLaren.addGear(1, 5.3);
//        // mcLaren.addGear(2, 10.3);
//        // mcLaren.addGear(3, 15.3);
//        mcLaren.operateClutch(true);
//        mcLaren.changeGear(1);
//        mcLaren.operateClutch(false);
//        System.out.println(mcLaren.wheelSpeed(1000));
//        mcLaren.changeGear(2);
//        System.out.println(mcLaren.wheelSpeed(3000));
    }

    private static void listen(){
        boolean quit = false;
        while(!quit){
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 0:
                    quit = true;
                    break;

                case 1:
                    btnPrint.onClick();
                    break;
            }
        }
    }
}

// The Gear class wouldn't normally be exposed to the public.
// Instantiating the inner Gear class. You can't create a Gear object without having an instance of Gearbox (mcLaren in this case)
// to create it from.
// This class is now private - hence it is inaccessible.
//Gearbox.Gear first = mcLaren.new Gear(1, 12.3);

//              // This wouldn't work.
//        Gearbox.Gear second = new Gearbox.Gear(1, 12.3);
//              // This also wouldn't work.
//        Gearbox.Gear third = new mcLaren.Gear(1, 12.3);

// System.out.println(first.driveSpeed(1000));