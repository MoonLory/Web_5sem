package model.factory;

import model.Appliances;
import model.Dishwasher;

/**
 * This factory create dishwasher
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public class DishwasherFactory implements Factory{

	@Override
	public Appliances createAppliances(String name, double powerWt, boolean socket) {
		return new Dishwasher("Dishwasher", 1.1, 5);
	}

}
