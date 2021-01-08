package model.factory;

import model.Appliances;
import model.Dishwasher;

/**
 * This factory create dishwasher
 * 
 * @author Andrei Grishkin
 * @version 1.2
 */
public class DishwasherFactory implements Factory{

	@Override
	public Appliances createAppliances() {
		return new Dishwasher("Dishwasher", 1.2, 5);
	}

}
