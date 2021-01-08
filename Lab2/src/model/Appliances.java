package model;

/**
 * Abstract class witch describes appliances
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public abstract class Appliances {
	
	/**
	 * name of the appliance
	 */
	protected String name;
	/**
	 * power of the appliance
	 */
	protected double powerWt; 
	/**
	 * is the appliance plugged in
	 */
	protected boolean voltage;
	
	/**
	 * Constructor, creates appliances with name, power and voltage
	 * 
	 * @param name name of the appliances
	 * @param powerWt power of the appliance
	 */
	Appliances(String name, double powerWt){
		this.name = name;
		this.powerWt = powerWt;
		this.voltage = false;
	}
	
	/**
	 * getter of name
	 * 
	 * @return name of the appliances
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * getter of power
	 * 
	 * @return power of the appliances
	 */
	public double getPower() {
		return powerWt;
	}
	
	/**
	 * getter of voltage
	 * 
	 * @return voltage of the appliances
	 */
	public boolean getvoltage() {
		return voltage;
	}
	
	/**
	 * name setter
	 * 
	 * @param name name of the appliances
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * power setter
	 * 
	 * @param power power of the appliances
	 */
	public void setPower(double power) {
		this.powerWt = power;
	}
	
	/**
	 * voltage setter
	 * 
	 * @param voltage voltage of the appliances
	 */
	public void setvoltage(boolean voltage) {
		this.voltage = voltage;
	}
	
	/**
	 * short review of the appliances
	 */
	public abstract void review();
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Appliances appl = (Appliances) obj;
		
		return name == appl.name && powerWt == appl.powerWt && voltage == appl.voltage;
	}
	
	@Override
	public String toString() {
		return String.format("Name: %s\nPowerWt: %2f\nvoltage: %d", this.name, this.powerWt, this.voltage);
	}
	
	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result += 17 * (voltage == true ? 1 : 0);
		result += 17 * (result + (int)powerWt);
		return result;
	}
	
	}