package com.philco;

import static com.philco.ThreadColor.ANSI_GREEN;
import static com.philco.ThreadColor.ANSI_PURPLE;
import static com.philco.ThreadColor.ANSI_RED;

public class Main {

    // You can never assume that the threads will run in the same order. It's up to the system to schedule when the thread will run.
    // Even if we set the priority of the execution, can can't still assume they will be in order.
    public static void main(String[] args) {


        // This is the application running our main thread.
        System.out.println(ANSI_PURPLE + "Hello from the main thread.");

        // We're not allowed to start the same instance of a thread more than once.
        Thread anotherThread = new AnotherThread();

        // '== Another Thread ==' is appended to the sout in the 'AnotherThread' class.
        anotherThread.setName(" == Another Thread == ");

        // The start method enables the JVM to run the run method we created in the 'AnotherThread' class.
        anotherThread.start();

        // This will now make 'anotherThread' run on the 'Main' thread which the main method is running on.
        // This is why you don't want to call the main method directly, but you want to call the start method instead.
        // 'anotherThread.setName(" == Another Thread == ");' is ignored.
        // anotherThread.run();

        // AnotherThread is called a named class. Below is how you create an anonymous class.
        new Thread() {
            public void run(){
                System.out.println(ANSI_GREEN + "Hello from the anonymous class thread.");
            }
        }.start();


        // Creating a new thread based on the 'MyRunnable' class we created.
//        Thread myRunnableThread = new Thread(new MyRunnable());
//        myRunnableThread.start();

                    // OR

        // USING AN ANONYMOUS FUNCTION
        // Because the 'MyRunnable' class does not extend the Thread class, we have to pass the MyRunnable instance as a parameter to the
        // Thread constructor.
        Thread myRunnableThread = new Thread(new MyRunnable(){
            @Override
            public void run() {
                // This will run the sout in the run method in 'MyRunnable' class.
                // super.run();

                // Output would be from this anonymous class this time because we have overridden the run method in the MyRunnable class.
                System.out.println(ANSI_RED + "Hello from the anonymous class's implementation of the run() - overridding the run() in MyRunnable");


                // Join: When you join a first thread to a second thread, the first thread waits for the second thread to finish execution and then it (the first
// thread) continues from where it left off.

                // In this case, myRunnableThread will be run, then wait 3 secs (because anotherThread will put itself to sleep). "Hello from
                // the anonymous class's in..." will be printed before printing out "anotherThread terminated, so...." (as soon as
                // anotherThread has woken up).
                try {
                    // Call the thread (anotherThread) that we want to join the current thread (myRunnableThread) to.
                    // The overloaded 'join' method times out 'anotherThread' at 2 seconds. Rem: there is a sleep time of 5 seconds  in the
                    // AnotherThread class, so this time out will be executed first.
                    //anotherThread.join(2000);
                    anotherThread.join(2000);
                    System.out.println(ANSI_RED + "anotherThread terminated or timed out, so I'm running again");
                }

                catch (InterruptedException e){
                    // This will only be executed if 'MyRunnable' anonymous class was interrupted for some reason.
                    System.out.println(ANSI_RED + "I could not wait after all. I was interrupted");
                }
            }
        });
        myRunnableThread.start();

        // The 3 second 'sleep' will be interruptted - the interrupt will be passed as an exception in the 'catch' block.
        // Interrupting the 'anotherThread' thread.
        anotherThread.interrupt();

        // This will throw an exception. We can't reuse the same instance of the anotherThread instance.
        // anotherThread.start();

        // Print out from the 'Main' thread.
        System.out.println(ANSI_PURPLE + "Hello again from the main thread");

    }
}
