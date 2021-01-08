package model.factory;

import model.Appliances;
import model.Fridge;

/**
 * This factory create Fridge
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class FridgeFactory implements Factory {

	@Override
	public Appliances createAppliances() {
		// TODO Auto-generated method stub
		return new Fridge("Fridge", 20.0, 80);
	}

}
