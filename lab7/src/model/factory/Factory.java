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
     * @param name
     * @param powerWt
     * @param socket
	 */
	public Appliances createAppliances(String name, double powerWt, boolean socket);
}
