package model;

/**
 * A machine that keeps the air in a building cool
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public class Conditioner extends Appliances{
	
	/**
	 * Number of temperature difference
	 */
	private int temperature;
	
	/**
	 * Constructor, creates Conditioner with name, power, socket and number of temperature difference
	 * 
	 * @param name name of the Conditioner
	 * @param power power of the Conditioner
	 * @param temperature number of temperature difference
	 */
	public Conditioner(String name, double power, int temperature){
		super(name,power);
		this.temperature = temperature;
	}
	
	/**
	 * getter of temperature difference
	 * 
	 * @return number of temperature difference
	 */
	public int gettemperature() {
		return temperature;
	}
	
	/**
	 * temperature difference setter
	 * 
	 * @param temperature number of temperature difference
	 */
	public void settemperature(int temperature) {
		this.temperature = temperature;
	}
	
	@Override
	public String review() {
		String str = String.format("The conditioner has power = %f and temperature difference = %d and conditioner plugged in = %s",this.getPower(),this.temperature, this.getSocket() ? "true" : "false");
		return  str;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Appliances appl = (Appliances) obj;
		Conditioner Conditioner = (Conditioner) obj;
		
		return appl.equals(this) && temperature == Conditioner.temperature;
	}
	
	@Override
	public String toString() {
		String stringFromSuper = super.toString();
		return String.format("%s\ntemperature: %d", stringFromSuper, this.temperature);
	}
	
	@Override
	public int hashCode() {
		int hashFromSuper = super.hashCode();
		return 17 * (temperature*19 + hashFromSuper);
	}
}
