package model.romoteManagment;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import model.Appliances;

/**
 * RMI stub
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public interface RemoteFactory extends Remote {
	
	/**
	 * This method create appliances
	 * @throws RemoteException exception
	 */
	public void createAppliancesList() throws RemoteException;
	
	/**
	 * This method show all appliances review
	 * @return review
	 * @throws RemoteException exception
	 */
	public String showAppliances() throws RemoteException;
	
	/**
	 * This method plug in socket
	 * @return review
	 * @throws RemoteException exception
	 */
	public String plugIn() throws RemoteException;
	
	/**
	 * This method select appliances by power in range
	 * @param start start range
	 * @param finish finish range
	 * @return list of appliances
	 * @throws RemoteException exception
	 */
	public String selectPower(int start, int finish) throws RemoteException;
	
	/**
	 * This method select appliances by mod
	 * @param mod mod of socket
	 * @return list of appliances
	 * @throws RemoteException exception
	 */
	public String selectSocket(Boolean mod) throws RemoteException;
	
	/**
	 * This method sort appliances by power
	 * @return list of appliances
	 * @throws RemoteException exception
	 */
	public String sortPower() throws RemoteException;
	
	/**
	 * This method select appliances by socket
	 * @return list of appliances
	 * @throws RemoteException exception
	 */
	public String sortSocket() throws RemoteException;
}
