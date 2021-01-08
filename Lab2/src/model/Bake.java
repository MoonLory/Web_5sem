package model;

/**
 * Oven, without using added liquid or fat.
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class Bake extends Appliances{
	
	/**
	 * Capacity of Bake
	 */
	private double capacityL;
	
	/**
	 * Constructor, creates Bake with name, power, voltage and capacityL
	 * 
	 * @param name name of the Bake
	 * @param powerWt power of the Bake
	 * @param capacityL capacity of Bake
	 */
	public Bake(String name, double powerWt, double capacityL) {
		super(name, powerWt);
		this.capacityL = capacityL;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * getter of capacity
	 * 
	 * @return capacity of Bake
	 */
	public double getCapacityL() {
		return capacityL;
	}
	
	/**
	 * capacity of Bake setter
	 * 
	 * @param capacityL capacity of Bake
	 */
	public void setCapacityL(double capacityL) {
		this.capacityL = capacityL;
	}
	
	@Override
	public void review() {
		// TODO Auto-generated method stub
		String str = String.format("The Bake has power = %f and capacity = %f and Bake plugged in = %s",this.getPower(),this.capacityL, this.getvoltage() ? "true" : "false");
		System.out.println(str);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Appliances appl = (Appliances) obj;
		Bake Bake = (Bake) obj;
		
		return appl.equals(this) && capacityL == Bake.capacityL;
	}
	
	@Override
	public String toString() {
		String stringFromSuper = super.toString();
		return String.format("%s\ncapacityL: %2f", stringFromSuper, this.capacityL);
	}
	
	@Override
	public int hashCode() {
		int hashFromSuper = super.hashCode();
		return 77 * ((int)capacityL*17 + hashFromSuper);
	}
}
