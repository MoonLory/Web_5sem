package view;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import controller.Controller;
import model.romoteManagment.RemoteFactory;

/**
 * This is the client program.
 *
 * @author Andrei Grishkin
 * @version 1.1
 */
public class Client {
	
	/**
     * The MainClient method
     * @param args command line parameters
     */
	public static void main(String args[]) { 
		try { 
		    RemoteFactory access = 
		        (RemoteFactory)Naming.lookup("rmi://localhost:8080/a"); 
		    access.createAppliancesList();
		    System.out.println(access.showAppliances());
		    System.out.println(access.plugIn());
		    System.out.println(access.selectPower(10, 60));
		    System.out.println(access.selectSocket(true));
		    System.out.println(access.sortPower());
		    System.out.println(access.sortSocket());
		} 
		catch(Exception ae) { 
		    System.out.println(ae); 
		} 
    } 
}


