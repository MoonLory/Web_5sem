package model.client;

import java.net.*;

import controller.Controller;

import java.io.*;

/**
 * This is the chat client program.
 *
 * @author Grishkin Andrei
 * @version 1.0
 */
public class Client {

    private String hostname;

    private int port;


    private String userName;

    /**
     * Constuctor of client
     *
     * @param hostname host name
     * @param port     port
     */
    public Client(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    /**
     * Connects to the server then enters the processing loop.
     */
    public void execute() {
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
     *
     * @param args args of comand line
     */
    public static void main(String[] args) {
        Client client = new Client("192.168.0.102", 8080);
        client.execute();
    }
}
