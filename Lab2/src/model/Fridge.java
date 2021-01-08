package model;

/**
 * Device that maintains a low temperature in a thermally insulated chamber
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class Fridge extends Appliances{
	
	/**
	 * Capacity of Fridge
	 */
	private double capacityL;
	
	/**
	 * Constructor, creates Fridge with name, power, voltage and capacityL
	 * 
	 * @param name name of the Fridge
	 * @param powerWt power of the Fridge
	 * @param capacityL capacity of Fridge
	 */
	public Fridge(String name, double powerWt, double capacityL) {
		// TODO Auto-generated constructor stub
		super(name, powerWt);
		this.capacityL = capacityL;
	}
	
	/**
	 * getter of capacity
	 * 
	 * @return capacity of Fridge
	 */
	public double getCapacityL() {
		return capacityL;
	}
	
	/**
	 * capacity of Fridge setter
	 * 
	 * @param capacityL capacity of Fridge
	 */
	public void setCapacityL(double capacityL) {
		this.capacityL = capacityL;
	}
	
	@Override
	public void review() {
		// TODO Auto-generated method stub
		String str = String.format("The Fridge has power = %f and capacity = %f and Fridge plugged in = %s",this.getPower(),this.capacityL, this.getvoltage() ? "true" : "false");
		System.out.println(str);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Appliances appl = (Appliances) obj;
		Fridge Fridge = (Fridge) obj;
		
		return appl.equals(this) && capacityL == Fridge.capacityL;
	}
	
	@Override
	public String toString() {
		String stringFromSuper = super.toString();
		return String.format("%s\ncapacityL: %2f", stringFromSuper, this.capacityL);
	}
	
	@Override
	public int hashCode() {
		int hashFromSuper = super.hashCode();
		return 17 * ((int)capacityL*113 + hashFromSuper);
	}
}
