package com.philco;

// We're going to have two threads in this application - one that will produce messages and the other will consume messages.

import java.util.Random;
import java.util.Random;


public class Main {

    public static void main(String[] args) {

        Message message = new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
    }
}

// METHODS THAT CAN ONLY BE CALLED IN THE SYNCHRONISED BLOCK OF CODE: (Purpose: each thread will now wait and release its lock on the message
// object when the loop condition passes)
// 1. WAIT - When one thread is currently executing a method, it tells all the other thread to that the method is in use and sends the wait signal to them.
// 2. NOTIFY
// 3. NOTIFYALL

// Initial problem: DEADLOCK - (When one thread hangs on to a lock and does not release it to other thread).
// The output was stuck because whilst one thread had the lock for one method, the other thread could not change the
// 'empty' field. This is where the WAIT, NOTIFY AND NOTIFY-ALL METHOD COME INTO PLAY.

class Message{

    // MAKING THREAD CALL 'NOTIFY ALL' WHEN THE 'message' IS CHANGED (in the 'write' method).
    private String message;
    private boolean empty = true;

    // We synchronised both the read and write methods as so that only one of them can be run at a time - also, only one synchronised method can
    // run at a time.

    // This method will be used by the consumer to read the message.
    public synchronized String read(){
   
        // Whilst there is a message, we're going to keep reading.
        while (empty){

            try {
                // We always want to call 'wait' within a for/while loop that is testing on what ever condition we're testing on because when a
                // thread is notified to wake up, there is no guarantee that it is being woken up becuae the condition it's waiting on has changed.
                // We're calling 'wait' here to ensure that the thread always goes back to the condition of the loop - to make sure that it's still valid -
                // this way we are never assuming that a thread is being woken up because the condition that it is waiting on might have changed.
                wait();
            }

            catch (InterruptedException e){

            }

        }
        // The empty variable will be true when there's no message to read.
        empty = true;
        // 'notifyAll' is used to wake up every thread. 'notify' will only wake up specific threads (e.g only one thread will be woken up).
        // 'notifyall' is conventionally used.
        // Notifies other threads when one thread has exited the while loop
        notifyAll();
        return message;
    }

    // This will be used by the producer to write a message.
    public synchronized void write(String message){

        // Checking to see if the message empty. If it isn't (aka empty is false), we're going to loop until the message is empty. If it is empty,
        // we are going to set empty to false and then write the message.
        while (empty){

            try {
                wait();
            }

            catch (InterruptedException e){

            }

        }
        empty = false;
        // MAKING THREAD CALL 'NOTIFY ALL' WHEN THE 'message' IS CHANGED (in the 'write' method).
        // Notifies the other threads that you have changed the message.
        notifyAll();
        this.message = message;
    }
}

// The Producer Class.
class Writer implements Runnable {

    private Message message;

    public Writer(Message message) {
        this.message = message;
    }

    public void run(){
        String messages[] = {
                "First",
                "Second",
                "THird",
                "Fourth"
        };
        // Used to put the threads randomly to sleep.
        Random random = new Random();

        for (int i = 0; i < messages.length; i++){
            message.write(messages[i]);
            // The thread will sleep for 2 seconds randomly. The producer thread will sleep for up to 2 seconds after writing a message.
            try {
                Thread.sleep(random.nextInt(2000));
            }

            catch (InterruptedException e){

            }
        }

        // Last item in 'message' object.
        message.write("Finished");
    }
}


// This is the Consumer Class
class Reader implements Runnable{

    private Message message;

    public Reader(Message message) {
        this.message = message;
    }

    public void run(){
        Random random = new Random();
        // !latestMessage - waiting for the message to say that it's finished to be received aka waiting for the last item in the message received.
        // "Finished" should match the one in the 'Writer' class (in the run method).
        // Loops through the message received whilst waiting for the last message ("Finished").
        for (String latestMessage = message.read(); !latestMessage.equals("Finished"); latestMessage = message.read()) {
            System.out.println(latestMessage);
        }

        // Setting sleep time randomly
        try {
            Thread.sleep(random.nextInt(2000));
        }
        catch (InterruptedException e){

        }
    }
}















