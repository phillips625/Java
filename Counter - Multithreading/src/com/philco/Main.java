package com.philco;


// Heap: Application memory that all threads share.
// Thread stack: Every thread has a thread stack (which is a block of memory) which only that thread can have access to.
// Thread 1 cannot access thread 2's stack, but they both have access to the heap.
// Consequently, this means that every thread shares the same copy of an object. So when there is a change to the instance variables,
// private int i;, by one thread, the other thread sees that change and picks it up from there (in the next iteration).
// In this example below, when we created the local i variable, JVM created a stack for each thread and since no other thread has access to
// each iteration is executed without other threads knowing about it. Conversely, since the instance variable, i , is stored in the heap, and all
// threads has access to the same heap, if one changes i, other variables see that change and then picks up that change for the next iteration.
// Also, notice that thread 1 and thread 2 outputs switch intermittently. This is because there are multiple points where a thread can be suspended.
// - namely, in between iterations, before i is discontinue, suspension points between the print statement, there are also delays between when
// the thread is suspended and the println line (this delay can cause other threads to either increment the loop or print a previously suspended
// execution), etc.

// 'Race Condition' aka 'Interleaving': When two or more threads share the same variable in the heap aka when 2 or more threads are writing to a shared resource. There
// are no problems when these threads only read the instance variable, the problems arise when they try to change the variable.



//public class Main {
//
//    public static void main(String[] args) {
//
//        // A real world application will not have separate objects.
//
//        CountDown countDown1 = new CountDown();
//        CountDown countDown2 = new CountDown();
//
//        // In order to halt the interference (caused by having 2 threads having access to the same heap), we can create one 'object' for one thread
//        // and another object for the second thread.
//        // This solution would not work in real world applications - images the objects where bank accounts or employee records, we can't
//        // provide each thread with a different object.
//        // Create 2 threads
//        CountdownThread t1 = new CountdownThread(countDown1);
//        t1.setName("Thread 1");
//        CountdownThread t2 = new CountdownThread(countDown2);
//        t2.setName("Thread 2");
//
//        // Starts both threads
//        t1.start();
//        t2.start();
//
//    }
//}

                                        // OR
public class Main {

    public static void main(String[] args) {

        CountDown countDown = new CountDown();

        // In order to halt the interference (caused by having 2 threads having access to the same heap), we can create one 'object' for one thread
        // and another object for the second thread.
        // Create 2 threads
        CountdownThread t1 = new CountdownThread(countDown);
        t1.setName("Thread 1");
        CountdownThread t2 = new CountdownThread(countDown);
        t2.setName("Thread 2");

        // Starts both threads
        t1.start();
        t2.start();

    }
}

class CountDown{

    // This is an instance variable. When 'i' is initialised here (as opposed to as a local variable in the for loop), the result of
    // sout will be different. The local variable, 'int i', is stored in the thread's stack (each thread has its own copy of the local variable).
    // Whereas the instance variable,i, below is stored in the heap.
    private int i;

                // 1.  Synchronised method
    //// Java gives us a way to control which thread can change a value in a heap (to avoid 'Race Condition').
//// The process of controlling when thread executes code (and therefore when they can access the heap) is called synchronisation.
//// We can synchronised methods and statements. When we synchronise a method, only one thread can execute that at a time. ONLY ONE THREAD CAN
//// RUN A SYNCHRONISED METHOD. If a class has 3 synchronised methods, then ONLY ONE OF THESE METHODS CAN RUN AT A TIME AND ONLY ON ONE THREAD -
//// this way, threads can't interleave when running a synchronised method.
//// If a field/instance variable is accessed by a synchronised method, then that field is prone to interference from other threads - hence
//// We have to synchronise all areas that we think interference can happen.

    // From t1.start();
    //      t2.start();
    // Now the output will be in order from 10 - 1 for each thread. When t1 is running this function, t2 will have no access to it.
    // Constructors cannot be synchronised - when the constructor is being used by one thread, it will not be available fot use by other
    // threads anyway.
//
//    public synchronized void doCountDown(){
//        String color;
//        switch (Thread.currentThread().getName()){
//
//            // Setting the color
//            // We're going to have 2 threads running at the same time.
//
//            // Thread 1 is the name of the thread
//            case "Thread 1":
//                color = ThreadColor.ANSI_CYAN;
//                break;
//            case "Thread 2":
//                color = ThreadColor.ANSI_PURPLE;
//                break;
//            default:
//                color = ThreadColor.ANSI_GREEN;
//        }

            // This is the preferred method FOR SYNCHRONISATION - AS WE DO NOT WANT TO SYNCHRONISE TOO MUCH CODE. We also don't want to
    // synchronise a variable as this will affect performance and the user experience. We want to keep the amount of we synchronise to an absolute minimum
    // - we only want to synchronise critical parts of the code.

                // 2. Synchronise each block of statement you want to synchronise - in this case, we're synchronising the for loop
    // based on the 'color' field.

    public void doCountDown(){
        String color;
        switch (Thread.currentThread().getName()){

            // Setting the color
            // We're going to have 2 threads running at the same time.

            // Thread 1 is the name of the thread
            case "Thread 1":
                color = ThreadColor.ANSI_CYAN;
                break;
            case "Thread 2":
                color = ThreadColor.ANSI_PURPLE;
                break;
            default:
                color = ThreadColor.ANSI_GREEN;
        }

        // Counting down

        // Will make use of the local variable 'i'.
        // for (int i = 10; i > 0; i--) {
                    //OR
        // Will make use of the instance variable.

        // DOES NOT WORK WELL
        // 'synchronized (color)' still does not work as we are using the local variable, 'color'. Each thread has a copy of 'Color' and
        // the threads interchange in their execution.
        // LESSON: DO NOT USE LOCAL VARIABLES TO SYNCHRONISE!
       //  synchronized (color){

        // WORKS WELL AS WE'RE SYNCHRONISING BASED ON THE OBJECT - only one thread can run the for loop at a time by using the lock associated
        // with the object (t1 and t2 in this case).

        // METHODS THAT CAN ONLY BE CALLED IN THE SYNCHRONISED BLOCK OF CODE:
        // 1. WAIT
        // 2. NOTIFY
        // 3. NOTIFYALL
        synchronized (this){
            for (i = 10; i > 0; i--) {

                System.out.println(color + Thread.currentThread().getName() + " : i = " + i);
            }
        }
    }
}

class CountdownThread extends Thread{

    private CountDown threadCountdown;

    public CountdownThread (CountDown countDown){
        threadCountdown = countDown;
    }

    public void run(){
        // Executes the method we created in the 'CountDown' class.
        threadCountdown.doCountDown();
    }
}

// Thread safe: This means that the developer has synchronised all the appropriate methods and block of code and you don't have to worry about
// race condition.