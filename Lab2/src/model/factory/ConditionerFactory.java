package model.factory;

import model.Appliances;
import model.Conditioner;

/**
 * This factory create Conditioner
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class ConditionerFactory implements Factory{

	@Override
	public Appliances createAppliances() {
		// TODO Auto-generated method stub
		return new Conditioner("Conditioner", 1000.0, 15.0);
	}

}
