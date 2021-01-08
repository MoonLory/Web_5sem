package model.manager;

import java.util.ArrayList;

import model.Appliances;
import model.comparator.PowerComparator;
import model.comparator.SocketComparator;

/**
 * This is manager that controls appliances
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public class Manager {
	
	/**
	 * Plug appliances in socket 
	 * 
	 * @param obj appliances which is used
	 */
	public void plugInSocket(Appliances obj) {
		if(obj.getSocket()) {
			System.out.println("The appliance is already plugged in");
		}else {
			obj.setSocket(true);
		}
	}
	
	/**
	 * Unplugs appliances from socket
	 * 
	 * @param obj appliances which is used
	 */
	public void unplugFromSocket(Appliances obj) {
		if(obj.getSocket()) {
			obj.setSocket(false);
		}else {
			System.out.println("The appliance was not plugged in");
		}
	}
	
	/**
	 * Find appliances by socket
	 * 
	 * @param type condition to find
	 * @param array list of appliances
	 * @return selected appliances ArrayList
	 */
	public ArrayList<Appliances> findAppliancesSocket(boolean type, ArrayList<Appliances> array) {
		ArrayList<Appliances> al = new ArrayList<>();
		for (Appliances i : array) {
			if(i.getSocket()==type) {
				al.add(i);
			}
		}
		return al;
	}
	
	/**
	 * Find appliances by power
	 * 
	 * @param min condition to find
	 * @param max condition to find
	 * @param array list of appliances
	 * @return selected appliances ArrayList
	 */
	public ArrayList<Appliances> findAppliancesPower(double min, double max, ArrayList<Appliances> array) {
		ArrayList<Appliances> al = new ArrayList<>();
		for (Appliances i : array) {
			if(i.getPower() >= min && i.getPower() <= max) {
				al.add(i);
			}
		}
		return al;
	}
	
	/**
	 * Sort appliances by power
	 * 
	 * @param array list of appliances
	 * @return sorted appliances ArrayList
	 */
	public ArrayList<Appliances> sortByPower(ArrayList<Appliances> array){
		PowerComparator comparator = new PowerComparator();
		array.sort(comparator);
		return array;
	} 
	
	/**
	 * Sort appliances by socket
	 * 
	 * @param array list of appliances
	 * @return sorted appliances ArrayList
	 */
	public ArrayList<Appliances> sortBySocket(ArrayList<Appliances> array){
		SocketComparator comparator = new SocketComparator();
		array.sort(comparator);
		return array;
	}
	
	/**
	 * Print all ArrayList
	 * 
	 * @param array list of appliances
	 */
	public void printArray(ArrayList<Appliances> array) {
		for (Appliances i : array) {
			i.review();
		}
	}

	/**
	 * Print all power
	 *
	 * @param array list of appliances
	 */
	public void printPower (ArrayList<Appliances> array) {
		double sumPower=0.0;
		for (Appliances i : array) {
			sumPower+=i.getPower();
		}
		System.out.println("The appliances power is "+sumPower) ;
	}
}
