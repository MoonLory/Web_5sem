package view;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import controller.Controller;
import model.romoteManagment.RemoteFactory;

/**
 * This is the server program.
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public class Server {
	
	/**
     * The MainClient method
     * @param args command line parameters
     */
	public static void main(String args[]) { 
        try { 
        	
            RemoteFactory stub = new Controller(); 
            
            LocateRegistry.createRegistry(8080); 
  
            Naming.rebind("rmi://localhost:8080/a",stub);
            System.out.println("Server start work");
            Thread.sleep(Integer.MAX_VALUE);
        } 
        catch(Exception ae) { 
        	Controller.logger.error(ae.getMessage());
        } 
    } 
}
