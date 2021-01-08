package model;

/**
 * Device that maintains a low temperature in a thermally insulated chamber
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public class Fridge extends Appliances{
	
	/**
	 * weight of fridge
	 */
	private double weight;
	
	/**
	 * Constructor, creates fridge with name, power, socket and weight
	 * 
	 * @param name name of the fridge
	 * @param power power of the fridge
	 * @param weight weight of fridge
	 */
	public Fridge(String name, double power, double weight) {
		super(name, power);
		this.weight = weight;
	}
	
	/**
	 * getter of weight
	 * 
	 * @return weight of fridge
	 */
	public double getweight() {
		return weight;
	}
	
	/**
	 * weight of fridge setter
	 * 
	 * @param weight weight of fridge
	 */
	public void setweight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String review() {
		String str = String.format("The fridge has power = %f and weight = %f and fridge plugged in = %s",this.getPower(),this.weight, this.getSocket() ? "true" : "false");
		return  str;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Appliances appl = (Appliances) obj;
		Fridge fridge = (Fridge) obj;
		
		return appl.equals(this) && weight == fridge.weight;
	}
	
	@Override
	public String toString() {
		String stringFromSuper = super.toString();
		return String.format("%s\nweight: %2f", stringFromSuper, this.weight);
	}
	
	@Override
	public int hashCode() {
		int hashFromSuper = super.hashCode();
		return 17 * ((int)weight*113 + hashFromSuper);
	}
}
