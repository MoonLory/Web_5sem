package model.factory;

import model.Appliances;
import model.VacuumCleaner;

/**
 * This factory create Vacuum cleaner
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class VacuumCleanerFactory implements Factory{

	@Override
	public Appliances createAppliances() {
		// TODO Auto-generated method stub
		return new VacuumCleaner("VacuumCleaner", 64.0, 5.0);
	}

}
