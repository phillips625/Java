package com.philco;

import static com.philco.ThreadColor.ANSI_RED;

/**
 * Created by PhillipsDaramola on 08/11/2017.
 */

                    // Question.

    // When should we subclass the Thread class or implement Runnable (which involves passing an instance of the class to the Thread constructor)?
    // Ans: Most developers use the 'Runnable' interface because it is convenient. There are many methods in the Java API that would require
    // a Runnable instance passed to them. Since the introduction of lambda, it's become more convenient to use Anonymous Runnable Instances!
public class MyRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(ANSI_RED + "Hello from MyRunnable's implementation of the run method.");
    }
}
