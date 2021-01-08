package model.client;

import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import controller.Controller;

import java.io.*;
 
/**
 * This is the chat client program.
 *
 * @author Grishkin Andrei
 * @version 1.0
 */
public class Client extends Thread{
	
    private String hostname;
    Socket socket;
    private int port;
    
    
    private String userName;
 
    /**
     * Constuctor of client
     * @param hostname host name
     * @param port port
     */
    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
        Scanner in = new Scanner(System.in);
    	
    	System.out.println("\nEnter your name: ");
    	this.userName = in.nextLine();
    }
    
    /**
     * Connects to the server then enters the processing loop.
     */
    public void run() {
        try {
            Socket socket = new Socket(hostname, port);
            
            System.out.println("Connected to the chat server");
            
            new ReadThread(socket, this).start();
            new WriteThread(socket, this).start();
            
        } catch (UnknownHostException ex) {
        	System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
        	System.out.println("I/O Error: " + ex.getMessage());
        }
 
    }
    
    /**
     * setter name of client
     *
     */
    void setUserName(String userName) {
        this.userName = userName;
    }
    
    /**
     * enter name of client
     *
     * @return client name
     */
    String getUserName() {
        return this.userName;
    }
 
    /**
     * Runs the client as an application with a closeable frame.
     * @param args args of comand line
     */
    public static void main(String[] args) {
        Client client = new Client("192.168.1.6", 8080);
        client.run();
    }
}
