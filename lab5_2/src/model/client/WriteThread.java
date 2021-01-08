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
    private Socket socket;
    private Client client;
 
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
    	Scanner in = new Scanner(System.in);
        writer.println("!"+client.getUserName());
 
        String text;
 
        do {
        	System.out.print("[" + client.getUserName() + "]: ");
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