package model.factory;

import model.Appliances;
import model.Bake;

/**
 * This factory create Bake
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class BakeFactory implements Factory{

	@Override
	public Appliances createAppliances() {
		// TODO Auto-generated method stub
		return new Bake("Bake",5000.0,3.0);
	}

}
