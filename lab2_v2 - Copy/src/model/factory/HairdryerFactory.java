package model.factory;

import model.Appliances;
import model.Hairdryer;

/**
 * This factory create Hairdryer
 * 
 * @author Andrei Grishkin
 * @version 1.2
 */
public class HairdryerFactory implements Factory{

	@Override
	public Appliances createAppliances() {
		return new Hairdryer("Hairdryer", 2.1, 80.0);
	}

}
