package model.factory;

import model.Appliances;
import model.VacuumCleaner;

/**
 * This factory create vacuum cleaner
 * 
 * @author Andrei Grishkin
 * @version 1.2
 */
public class VacuumCleanerFactory implements Factory{

	@Override
	public Appliances createAppliances() {
		return new VacuumCleaner("VacuumCleaner", 2.2, 45.0);
	}

}
