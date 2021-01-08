package model;

/**
 * A machine that washes dirty plates, cups, forks, etc.
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class Dishwasher extends Appliances{
	
	/**
	 * Number of operating mode
	 */
	private int operatingMode;
	
	/**
	 * Constructor, creates Dishwasher with name, power, voltage and number of operating mode
	 * 
	 * @param name name of the Dishwasher
	 * @param power power of the Dishwasher
	 * @param operatingMode number of operating mode
	 */
	public Dishwasher(String name, double power, int operatingMode){
		super(name,power);
		this.operatingMode = operatingMode;
	}
	
	/**
	 * getter of operating mode
	 * 
	 * @return number of operating mode
	 */
	public int getOperatingMode() {
		return operatingMode;
	}
	
	/**
	 * Operating mode setter
	 * 
	 * @param operatingMode number of operating mode
	 */
	public void setOperatingMode(int operatingMode) {
		this.operatingMode = operatingMode;
	}
	
	@Override
	public void review() {
		// TODO Auto-generated method stub
		String str = String.format("The Dishwasher has power = %f and operating mode = %d and Dishwasher plugged in = %s",this.getPower(),this.operatingMode, this.getvoltage() ? "true" : "false");
		System.out.println(str);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Appliances appl = (Appliances) obj;
		Dishwasher Dishwasher = (Dishwasher) obj;
		
		return appl.equals(this) && operatingMode == Dishwasher.operatingMode;
	}
	
	@Override
	public String toString() {
		String stringFromSuper = super.toString();
		return String.format("%s\noperatingMode: %d", stringFromSuper, this.operatingMode);
	}
	
	@Override
	public int hashCode() {
		int hashFromSuper = super.hashCode();
		return 17 * (operatingMode*19 + hashFromSuper);
	}
}
