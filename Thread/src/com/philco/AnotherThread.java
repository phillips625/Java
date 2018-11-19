package com.philco;

import static com.philco.ThreadColor.ANSI_BLUE;

/**
 * Created by PhillipsDaramola on 08/11/2017.
 */

// This is the way to add thread to an APPLICATION/PROCESS.

public class AnotherThread extends Thread{

    @Override
    public void run() {
        // super.run();
        //Put the code we want executed in this thread
        // currentThread().getName() returns the current name of the thread that has been executing.
        System.out.println(ANSI_BLUE + "Hello from " + currentThread().getName());

        // Put this thread to sleep for 3 seconds to allow other threads to be run.
        try {
            // Thread.sleep(5000);
            Thread.sleep(5000);
        }

        // This exception will be thrown if another thread woke us up.
        // A thread can be interrupted hence the reason why we have this block of code.
        // For example,  an INTERRUPT COULD END THE EXECUTION OF THIS THREAD.
        // There are 2 ways for a thread to know it is being interrupted:
        // 1. Catch the interrupted exception
        // 2. It should call the interrupted method priodically to check whether it is being interruoted.
        catch (InterruptedException e){
            System.out.println(ANSI_BLUE + "Another thread woke me up");

            // The AnotherThread instance will be terminated as soon as it is interrupted (thanks to the 'return' statement).
            return;
        }

        // This will tell us when 3 seconds has elapsed.
        System.out.println(ANSI_BLUE + "Three seconds has passed and now I'm awake");
    }
}


// Also, if we want to run a thread only once, then we can use an anonymous function (in the main function instead).
