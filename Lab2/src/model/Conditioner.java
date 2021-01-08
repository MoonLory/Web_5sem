package model;

/**
 * A machine that keeps the air in a building cool
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class Conditioner extends Appliances{
	
	/**
	 * temperature difference
	 */
	private double temperatureDifference;
	
	/**
	 * Constructor, creates Conditioner with name, power, voltage and temperature difference
	 * 
	 * @param name name of the Conditioner
	 * @param powerWt power of the Conditioner
	 * @param temperatureDifference temperature difference
	 */
	public Conditioner(String name, double powerWt, double temperatureDifference) {
		// TODO Auto-generated constructor stub
		super(name, powerWt);
		this.temperatureDifference = temperatureDifference;
	}
	
	/**
	 * getter temperature difference
	 * 
	 * @return temperature difference
	 */
	public double gettemperatureDifference() {
		return temperatureDifference;
	}
	
	/**
	 * temperature difference setter
	 * 
	 * @param temperatureDifference temperature difference
	 */
	public void settemperatureDifference(double temperatureDifference) {
		this.temperatureDifference = temperatureDifference;
	}
	
	@Override
	public void review() {
		// TODO Auto-generated method stub
		String str = String.format("The Conditioner has power = %f and temperature difference = %f and Conditioner plugged in = %s",this.getPower(),this.temperatureDifference, this.getvoltage() ? "true" : "false");
		System.out.println(str);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Appliances appl = (Appliances) obj;
		Conditioner Conditioner = (Conditioner) obj;
		
		return appl.equals(this) && temperatureDifference == Conditioner.temperatureDifference;
	}
	
	@Override
	public String toString() {
		String stringFromSuper = super.toString();
		return String.format("%s\ncapacityL: %2f", stringFromSuper, this.temperatureDifference);
	}
	
	@Override
	public int hashCode() {
		int hashFromSuper = super.hashCode();
		return 17 * ((int)temperatureDifference*117 + hashFromSuper);
	}
}
