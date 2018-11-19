                                // THIS IS TCPIP

package com.philco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // We're going to be using 'Socket' class instead of 'SocketServer' class (like we did in the 'EchoServer' project).
        // First argument - address of the host/server we are going to connect to - (localhost is basically my computer). You can also
        // use 127.0.0.1.
        // Second argument - port number (this should be the same as the host you want to connect to). API should give you info about this (
        // if you're not writing for both the host and the server).
        try (Socket socket = new Socket("localhost", 5000)){

            // Setting the time out to 5 seconds
            socket.setSoTimeout(5000);
            BufferedReader echoes = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // We're using 'PrintWriter' to send the String to the server.
            // Always set the auto flush to true. 'true' flushes the output automatically.
            PrintWriter stringToEcho = new PrintWriter(socket.getOutputStream(), true);

            Scanner scanner = new Scanner(System.in);
            String echoString;
            String response;

            // Using do while so that we run the code at least once.
            do {
                System.out.println("Enter string to be echoed: ");
                echoString = scanner.nextLine();

                stringToEcho.println(echoString);

                if (!echoString.equals("exit")){
                    // This is the response we get from the server.
                    response = echoes.readLine();
                    System.out.println(response);
                }
            }

            while (!echoString.equals("exit"));

        }

        catch (SocketTimeoutException e){
            System.out.println("The socket timed out");
        }

        catch (IOException e){
            System.out.println("Client Error: " + e.getMessage());
        }

    }
}
