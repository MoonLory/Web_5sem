package model.factory;

import model.Appliances;
import model.Hairdryer;

/**
 * This factory create Hairdryer
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class HairdryerFactory implements Factory{

	@Override
	public Appliances createAppliances() {
		// TODO Auto-generated method stub
		return new Hairdryer("Hairdryer", 100.0, 15);
	}

}
