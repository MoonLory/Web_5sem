package model.server;

import java.io.*;
import java.net.*;
import java.util.*;

import controller.Controller;
import exception.ServerException;
 
/**
 * This thread handles connection for each connected client, so the server
 * can handle multiple clients at the same time.
 *
 * @author Grishkin Andrei
 * @version 1.0
 */
public class ClientHandler extends Thread {
    private Socket socket;
    private Server server;
    private PrintWriter writer;
    FileWriter fileWriter;
    
    Date date;
    
    public ClientHandler(Socket socket, Server server) throws ServerException {
    	date = new Date();
    	if(socket==null) {
    		throw new ServerException("Socket = null");
    	}
        this.socket = socket;
        this.server = server;
    }
 
    public void run() {
        try {
        	
        	fileWriter = new FileWriter("notes.txt", true);
        	
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
 
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
 
            printUsers();
 
            String userName = reader.readLine();
            server.addUserName(userName);
            
            fileWriter.write(date.toString()+" "+userName+"\n");
            fileWriter.flush();
            
            String serverMessage = "New user connected: " + userName;
            server.broadcast(serverMessage, this);
 
            String clientMessage;
 
            do {
                clientMessage = reader.readLine();
                serverMessage = "[" + userName + "]: " + clientMessage;
                server.broadcast(serverMessage, this);
 
            } while (!clientMessage.equals("bye"));
 
            server.removeUser(userName, this);
            socket.close();
 
            serverMessage = userName + " has quitted.";
            server.broadcast(serverMessage, this);
 
        } catch (IOException ex) {
            System.out.println("Error in UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
 
    /**
     * Sends a list of online users to the newly connected user.
     */
    void printUsers() {
        if (server.hasUsers()) {
            writer.println("Connected users: " + server.getUserNames());
        } else {
            writer.println("No other users connected");
        }
    }
 
    /**
     * Sends a message to the client.
     * @param message text message
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}