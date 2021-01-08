package model;

/**
 * Percussion machine providing, along with the impact, rotation of the working tool
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public class Dishwasher extends Appliances{
	
	/**
	 * Number of operating mode
	 */
	public int mode;
	
	/**
	 * Constructor, creates dishwasher with name, power, socket and operating mode
	 * 
	 * @param name name of the dishwasher
	 * @param power power of the dishwasher
	 * @param mode Number of operating mode
	 */
	public Dishwasher(String name, double power, int mode) {
		super(name, power);
		this.mode = mode;
		}
	
	/**
	 * getter of operating mode
	 * 
	 * @return operating mode
	 */
	public int getNumberOfConditioners() {
		return mode;
	}
	
	/**
	 * Number of operating mode setter
	 * 
	 * @param mode operating mode
	 */
	public void setNumberOfConditioners(int mode) {
		this.mode = mode;
	}
	
	@Override
	public void review() {
		String str = String.format("The dishwasher has power = %f and operating mode = %d and dishwasher plugged in = %s",this.getPower(),this.mode, this.getSocket() ? "true" : "false");
		System.out.println(str);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Appliances appl = (Appliances) obj;
		Dishwasher dishwasher = (Dishwasher) obj;
		
		return appl.equals(this) && mode == dishwasher.mode;
	}
	
	@Override
	public String toString() {
		String stringFromSuper = super.toString();
		return String.format("%s\nmode: %d", stringFromSuper, this.mode);
	}
	
	@Override
	public int hashCode() {
		int hashFromSuper = super.hashCode();
		return 17 * (mode*61 + hashFromSuper);
	}
}
