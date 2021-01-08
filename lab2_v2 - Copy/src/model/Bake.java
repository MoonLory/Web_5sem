package model;

/**
 * Element of household appliances for smoothing wrinkles and creases on clothes
 * 
 * @author Andrei Grishkin
 * @version 1.2
 */
public class Bake extends Appliances{
	
	/**
	 * weight of bake
	 */
	private double weight;
	
	/**
	 * Constructor, creates bake with name, power, socket and weight
	 * 
	 * @param name name of the bake
	 * @param power power of the bake
	 * @param weight weight of bake
	 */
	public Bake(String name, double power, double weight) {
		super(name, power);
		this.weight = weight;
		}
	
	/**
	 * getter of weight
	 * 
	 * @return weight of bake
	 */
	public double getweight() {
		return weight;
	}
	
	/**
	 * weight of bake setter
	 * 
	 * @param weight weight of bake
	 */
	public void setweight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public void review() {
		String str = String.format("The bake has power = %f and weight = %f and bake plugged in = %s",this.getPower(),this.weight, this.getSocket() ? "true" : "false");
		System.out.println(str);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Appliances appl = (Appliances) obj;
		Bake bake = (Bake) obj;
		
		return appl.equals(this) && weight == bake.weight;
	}
	
	@Override
	public String toString() {
		String stringFromSuper = super.toString();
		return String.format("%s\nweight: %2f", stringFromSuper, this.weight);
	}
	
	@Override
	public int hashCode() {
		int hashFromSuper = super.hashCode();
		return 77 * ((int)weight*17 + hashFromSuper);
	}
}
