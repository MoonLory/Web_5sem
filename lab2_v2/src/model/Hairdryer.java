package model;

/**
 * A machine, used for drying a hair
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public class Hairdryer extends Appliances{
	
	/**
	 * temperature difference
	 */
	private double temperature;
	
	/**
	 * Constructor, creates Hairdryer with name, power, socket and temperature difference
	 * 
	 * @param name name of the Hairdryer
	 * @param power power of the Hairdryer
	 * @param temperature temperature difference
	 */
	public Hairdryer(String name, double power, double temperature) {
		super(name, power);
		this.temperature = temperature;
	}
	
	/**
	 * getter temperature difference
	 * 
	 * @return temperature difference
	 */
	public double gettemperature() {
		return temperature;
	}
	
	/**
	 * temperature difference setter
	 * 
	 * @param temperature temperature difference
	 */
	public void settemperature(double temperature) {
		this.temperature = temperature;
	}
	
	@Override
	public void review() {
		String str = String.format("The hairdryer has power = %f and temperature difference = %f and hairdryer plugged in = %s",this.getPower(),this.temperature, this.getSocket() ? "true" : "false");
		System.out.println(str);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Appliances appl = (Appliances) obj;
		Hairdryer Hairdryer = (Hairdryer) obj;
		
		return appl.equals(this) && temperature == Hairdryer.temperature;
	}
	
	@Override
	public String toString() {
		String stringFromSuper = super.toString();
		return String.format("%s\nweight: %2f", stringFromSuper, this.temperature);
	}
	
	@Override
	public int hashCode() {
		int hashFromSuper = super.hashCode();
		return 17 * ((int)temperature*117 + hashFromSuper);
	}
}
