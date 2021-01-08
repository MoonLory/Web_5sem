package model.factory;

import model.Appliances;

/**
 * Factory method
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public interface Factory {
	
	/**
	 * this method create new object
	 * 
	 * @return new object
	 */
	public Appliances createAppliances();
}
