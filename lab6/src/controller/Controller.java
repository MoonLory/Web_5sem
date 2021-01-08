package controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.Appliances;
import model.factory.ConditionerFactory;
import model.factory.Factory;
import model.factory.FridgeFactory;
import model.factory.BakeFactory;
import model.factory.DishwasherFactory;
import model.factory.HairdryerFactory;
import model.factory.VacuumCleanerFactory;

import model.manager.Manager;
import model.romoteManagment.RemoteFactory;

/**
 * Basic class demonstrates functions of classes
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public class Controller extends UnicastRemoteObject implements RemoteFactory {
	
	public Controller() throws RemoteException {
		super();
	}

	static Manager manager = new Manager();
	static Factory df = new ConditionerFactory();
	static Factory ff = new FridgeFactory();
	static Factory irf= new BakeFactory();
	static Factory pf = new DishwasherFactory();
	static Factory sf = new HairdryerFactory();
	Factory vcf = new VacuumCleanerFactory();
	static ArrayList<Appliances> alA = new ArrayList<>();
	static ArrayList<Appliances> array;
	static String div = "-----------------------------------\n";
	
	public static final Logger logger = LogManager.getLogger();
	
	/**
	 * This method create appliances
	 * @throws RemoteException exception
	 */
	public synchronized void createAppliancesList() throws RemoteException {
		alA.add(df.createAppliances());
		alA.add(ff.createAppliances());
		alA.add(irf.createAppliances());
		alA.add(pf.createAppliances());
		alA.add(sf.createAppliances());
		alA.add(vcf.createAppliances());
		Controller.logger.info("Appliances was created");
	}
	
	/**
	 * This method show all appliances review
	 * @return review
	 * @throws RemoteException exception
	 */
	public synchronized String showAppliances() throws RemoteException {
		String str = div;
		str += "Factory method\n";
		for(int i = 0; i<alA.size();i++) {
			str += alA.get(i).review() + "\n";
		}
		Controller.logger.info(str);
		return str;
	}
	
	/**
	 * This method plug in socket
	 * @return review
	 * @throws RemoteException exception
	 */
	public synchronized String plugIn() throws RemoteException {
		String str = div;
		str += "Plug in socket\n";
		manager.plugInSocket(alA.get(0));
		str += alA.get(0).review() + "\n";
		Controller.logger.info(str);
		return str;
	}
	
	/**
	 * This method select appliances by power in range
	 * @param start start range
	 * @param finish finish range
	 * @return list of appliances
	 * @throws RemoteException exception
	 */
	public synchronized String selectPower(int start, int finish) throws RemoteException {
		array = new ArrayList<>();
		String str = div;
		str += "Select power\n";
		array = manager.findAppliancesPower(start, finish, alA);
		str += manager.printArray(array);
		Controller.logger.info(str);
		return str;
	}
	
	/**
	 * This method select appliances by mod
	 * @param mod mod of socket
	 * @return list of appliances
	 * @throws RemoteException exception
	 */
	public synchronized String selectSocket(Boolean mod) throws RemoteException {
		array = new ArrayList<>();
		String str = div;
		str += "Select true socket\n";
		array = manager.findAppliancesSocket(mod, alA);
		str += manager.printArray(array);
		Controller.logger.info(str);
		return str;
	}
	
	/**
	 * This method sort appliances by power
	 * @return list of appliances
	 * @throws RemoteException exception
	 */
	public synchronized String sortPower() throws RemoteException {
		array = new ArrayList<>();
		String str = div;
		str += "Sort by power\n";
		array = manager.sortByPower(alA);
		str += manager.printArray(array);
		Controller.logger.info(str);
		return str;
	}
	
	/**
	 * This method select appliances by socket
	 * @return list of appliances
	 * @throws RemoteException exception
	 */
	public synchronized String sortSocket() throws RemoteException {
		array = new ArrayList<>();
		String str = div;
		str += "Sort by socket\n";
		array = manager.sortBySocket(alA);
		str += manager.printArray(array);
		Controller.logger.info(str);
		return str;
	}

}
