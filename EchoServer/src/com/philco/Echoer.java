package com.philco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by PhillipsDaramola on 18/02/2018.
 */
public class Echoer extends Thread{

    private Socket socket;

    public Echoer(Socket socket) {
        this.socket = socket;
    }

    // The run method will be called when the thread is started.

    @Override
    public void run() {

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true){
                String echoString = input.readLine();

                // Ensuring that the server has received the message
                System.out.println("Received the client input: " + echoString);

                if (echoString.equals("exits")){
                    break;
                }

                // Server will sleep for 15s each time the client sends a request.
                try {
                    Thread.sleep(15000);
                }

                catch (InterruptedException e){
                    System.out.println("Thread Interrupted");
                }

                output.println(echoString);
            }
        }

        catch (IOException e){
            System.out.println("Oops: " + e.getMessage());
        }

        finally {
            try {
                socket.close();
            }
            catch (IOException e){

            }
        }
    }
}
