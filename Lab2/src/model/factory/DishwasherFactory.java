package model.factory;

import model.Appliances;
import model.Dishwasher;

/**
 * This factory create Dishwasher
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class DishwasherFactory implements Factory{

	@Override
	public Appliances createAppliances() {
		// TODO Auto-generated method stub
		return new Dishwasher("Dishwasher", 10.8, 10);
	}
}
