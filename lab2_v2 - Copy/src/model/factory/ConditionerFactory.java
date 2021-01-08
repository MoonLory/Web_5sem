package model.factory;

import model.Appliances;
import model.Conditioner;

/**
 * This factory create Conditioner
 * 
 * @author Andrei Grishkin
 * @version 1.2
 */
public class ConditionerFactory implements Factory{

	@Override
	public Appliances createAppliances() {
		return new Conditioner("Conditioner", 0.8, 10);
	}
}
