package model.comparator;

import java.util.Comparator;

import model.Appliances;

/**
 * Appliances comparator by power
 * 
 * @author Andrei Grishkin
 * @version 1.2
 */
public class SocketComparator implements Comparator<Appliances>{

	@Override
	public int compare(Appliances o1, Appliances o2) {
		return Boolean.compare(o1.getSocket(), o2.getSocket());
	}

}
