package model.server;

import java.io.*;
import java.net.*;
import java.util.*;

import controller.Controller;
import exception.ServerException;
 
/**
 * This handles connection for each connected client
 * @author Andrei Grishkin
 * @version 1.0
 */
public class ServerHandler {
    private String userName;
    StringBuilder msg;
    FileWriter fileWriter;
    Date date;
    
    /**
     * Constructor creates empty client
     */
    public ServerHandler() throws ServerException, IOException {
    	date = new Date();
    	msg = new StringBuilder();
    	fileWriter = new FileWriter("notes.txt", true);
    }
 
    /**
     * Initialize empty user with name and groups
     */
    void init(String initstr) throws IOException {
    	
        this.userName = initstr;
		fileWriter.write(date.toString()+" "+initstr+"\n");
        fileWriter.flush();
        return;
    }
   
    /*
     * Getter of user name
     */
    String getUserName() {
    	return userName;
    }
    
    /*
     * Setter of user name
     */
    void setUserName(String string) {
    	this.userName = string;
    }
    
    /*
     * Message builder
     */
    public void append(String msg){
        this.msg.append(msg);
    }
    
    /**
     * Getter of message
     * @return msg
     */
    public StringBuilder getMessage(){
        return msg;
    }
    
    /*
     * Clear method of message
     */
    public void clear(){
        msg.setLength(0);
    }
 
}