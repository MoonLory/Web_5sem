package model;

/**
 * A machine, used for drying a hair
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class Hairdryer extends Appliances{
	
	/**
	 * Number of Dishwashers
	 */
	public int numberOfDishwashers;
	
	/**
	 * Constructor, creates Hairdryer with name, power, voltage and number of Dishwashers
	 * 
	 * @param name name of the Hairdryer
	 * @param powerWt power of the Hairdryer
	 * @param numberOfDishwashers number of Dishwashers
	 */
	public Hairdryer(String name, double powerWt, int numberOfDishwashers) {
		super(name, powerWt);
		this.numberOfDishwashers = numberOfDishwashers;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * getter of number of Dishwashers
	 * 
	 * @return number of Dishwashers
	 */
	public int getNumberOfDishwashers() {
		return numberOfDishwashers;
	}
	
	/**
	 * number of Dishwashers setter
	 * 
	 * @param numberOfDishwashers number of Dishwashers
	 */
	public void setNumberOfDishwashers(int numberOfDishwashers) {
		this.numberOfDishwashers = numberOfDishwashers;
	}
	
	@Override
	public void review() {
		// TODO Auto-generated method stub
		String str = String.format("The Hairdryer has power = %f and number of Dishwashers = %d and Hairdryer plugged in = %s",this.getPower(),this.numberOfDishwashers, this.getvoltage() ? "true" : "false");
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
		
		return appl.equals(this) && numberOfDishwashers == Hairdryer.numberOfDishwashers;
	}
	
	@Override
	public String toString() {
		String stringFromSuper = super.toString();
		return String.format("%s\nnumberOfDishwashers: %d", stringFromSuper, this.numberOfDishwashers);
	}
	
	@Override
	public int hashCode() {
		int hashFromSuper = super.hashCode();
		return 17 * (numberOfDishwashers*61 + hashFromSuper);
	}
}
