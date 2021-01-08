package model.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import controller.Controller;

/**
 * This thread is responsible for reading user's input and send it
 * to the server.
 * It runs in an infinite loop until the user types 'bye' to quit.
 *
 * @author Grishkin Andrei
 * @version 1.0
 */
public class WriteThread extends Thread {
    private PrintWriter writer;
    private final Socket socket;
    private final Client client;

    public WriteThread(Socket socket, Client client) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {

            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {

        //Console console = System.console();
        Scanner in = new Scanner(System.in);

        //String userName = console.readLine("\nEnter your name: ");
        System.out.println("\nEnter your name: ");
        String userName = in.nextLine();
        client.setUserName(userName);
        writer.println(userName);

        String text;

        do {
            System.out.print("[" + userName + "]: ");
            //text = console.readLine("[" + userName + "]: ");
            text = in.nextLine();
            writer.println(text);

        } while (!text.equals("bye"));

        try {
            socket.close();
        } catch (IOException ex) {
            System.out.print("Error writing to server: " + ex.getMessage());
        }
    }
}