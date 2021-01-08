package model.factory;

import model.Appliances;
import model.Bake;

/**
 * This factory create bake
 * 
 * @author Andrei Grishkin
 * @version 1.2
 */
public class BakeFactory implements Factory{

	@Override
	public Appliances createAppliances() {
		return new Bake("Bake",2.5,40.0);
	}

}
