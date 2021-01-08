package model.comparator;

import java.util.Comparator;

import model.Appliances;

/**
 * Appliances comparator by power
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public class PowerComparator implements Comparator<Appliances>{

	@Override
	public int compare(Appliances arg0, Appliances arg1) {
		return Double.compare(arg0.getPower(), arg1.getPower());
	}

}
