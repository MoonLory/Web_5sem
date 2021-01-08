package model.factory;

import model.Appliances;
import model.Fridge;

/**
 * This factory create fridge
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public class FridgeFactory implements Factory {

	@Override
	public Appliances createAppliances() {
		return new Fridge("Fridge", 0.15, 80);
	}

}
