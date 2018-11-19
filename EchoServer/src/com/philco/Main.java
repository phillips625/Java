                                    // THIS IS TCPIP

package com.philco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// 1. TCPIP - Handshake is required between the client and the server. The overhead is significant here.

// 2. UDP - User Datagram Protocol - Used for time sensitive applications like skype. No hand shake is required btw the server and the client.
// This is faster that TCPIP


                                                // RUN THE SERVER FIRST
                                                // THEN RUN THE CLIENT

                        // SERVER SOCKET
// You need to create the client socket/s to connect to the server.
public class Main {

    public static void main(String[] args) {

        // Creating Server's socket
        // PORT NUMBER can range from 0 - 2^16. Some numbers in that range are reserved.
        try (ServerSocket serverSocket = new ServerSocket(5000)){



            // You want the client to be able to read from the server as long as there is an internet connection - hence the reason why we
            // are using an infinite loop.
            while (true){
                // We're kicking off a new THREAD every time we accept a NEW connection - (unlike when we formally put all the connections
                // in one thread). This is done using the '.accept' method - The accpect method returns a new socket for the client.
                // '.start' kicks off the 'Echoer class' run method.
                new Echoer(serverSocket.accept()).start();
            }
        }
        catch (IOException e){
//            e.printStackTrace();
            System.out.println("Server exception: " + e.getMessage());
        }

    }
}


/*
                                // FORMER TRY BLOCK

        // Creating Server's socket
        // PORT NUMBER can range from 0 - 2^16. Some numbers in that range are reserved.
        try (ServerSocket serverSocket = new ServerSocket(5000)){



            // You want the client to be able to read from the server as long as there is an internet connection - hence the reason why we
            // are using an infinite loop.
            while (true){

                // Putting the next 4 lines of code in the while loop allows for multiple client connection.
                Socket socket = serverSocket.accept();
                System.out.println("Client Accepted");

                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // Always set the auto flush to true. 'true' flushes the output automatically.
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                String echoString = input.readLine();
                // This thread will be put to sleep for 15s
                try {
                    Thread.sleep(1500);
                }
                catch (InterruptedException e){
                    System.out.println("Thread Interrputed");
                }

                if (echoString.equals("exit")){
                    // Providing a way to break out of the loop
                    break;
                }
                output.println("Echo from server: " + echoString);
            }
        }


 */