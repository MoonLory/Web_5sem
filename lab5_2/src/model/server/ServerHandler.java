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
public class ServerHandler {
	
    private Server server;
    private String userName;
    
    Date date;
    
    /**
     * Constructor of user
     * @param server server
     * @throws ServerException return exception
     */
    public ServerHandler(Server server) throws ServerException {
    	date = new Date();
    	if(server == null) {
    		throw new ServerException("Server is null");
    	}
        this.server = server;
    }
 
    /**
     * Getter of user
     * @return user name
     */
    String getUserName() {
    	return userName;
    }
    
    /**
     * Setter of user
     * @param string user name
     */
    void setUserName(String string) {
    	this.userName = string;
    }
 
}