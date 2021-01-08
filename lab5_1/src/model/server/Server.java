package model.server;

import java.io.*;
import java.net.*;
import java.util.*;

import controller.Controller;
import exception.ServerException;

/**
 * 
 * @author Grishkin Andrei
 * @version 1.0
 */
public class Server {
	
    private int port;
    
    private Set<String> userNames = new HashSet<>();
    
    private Set<ClientHandler> userThreads = new HashSet<>();
    
    
    /**
     * 
     * @param port
     */
    public Server(int port) {
        this.port = port;
    }
 
    /**
     * 
     */
    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
        	System.out.println("Chat Server is listening on port " + port);
 
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New user connected");
                
                ClientHandler newUser = new ClientHandler(socket, this);
                userThreads.add(newUser);
               ;
                newUser.start();
 
            }
 
        } catch (IOException | ServerException ex) {
        	System.out.println("Error in the server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        Server server = new Server(8080);
        server.execute();
    }
 
    /**
     * Delivers a message from one user to others (broadcasting)
     * @param message
     * @param excludeUser
     */
    void broadcast(String message, ClientHandler excludeUser) {
        for (ClientHandler aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
    }
 
    /**
     * Stores username of the newly connected client.
     * @param userName user name 
     */
    void addUserName(String userName) {
        userNames.add(userName);
    }
 
    /**
     * When a client is disconneted, removes the associated username and UserThread
     * @param userName user name
     * @param aUser 
     */
    void removeUser(String userName, ClientHandler aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("The user " + userName + " quitted");
        }
    }
    
    /**
     * 
     * @return
     */
    Set<String> getUserNames() {
        return this.userNames;
    }
 
    /**
     * Returns true if there are other users connected (not count the currently connected user)
     */
    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }
}
