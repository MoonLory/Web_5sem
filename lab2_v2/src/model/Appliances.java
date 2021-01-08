package model;

/**
 * abstract class witch describes appliances
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public abstract class Appliances {
	
	/**
	 * name of the appliance
	 */
	protected String name;
	/**
	 * power of the appliance
	 */
	protected double power;
	/**
	 * is the appliance plugged in
	 */
	protected boolean socket;
	
	/**
	 * Constructor, creates appliances with name, power and socket
	 * 
	 * @param name name of the appliances
	 * @param power power of the appliance
	 */
	Appliances(String name, double power){
		this.name = name;
		this.power = power;
		this.socket = false;
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
		return power;
	}
	
	/**
	 * getter of socket
	 * 
	 * @return socket of the appliances
	 */
	public boolean getSocket() {
		return socket;
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
		this.power = power;
	}
	
	/**
	 * socket setter
	 * 
	 * @param socket socket of the appliances
	 */
	public void setSocket(boolean socket) {
		this.socket = socket;
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
		
		return name == appl.name && power == appl.power && socket == appl.socket;
	}
	
	@Override
	public String toString() {
		return String.format("Name: %s\npower: %2f\nSocket: %d", this.name, this.power, this.socket);
	}
	
	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result += 17 * (socket == true ? 1 : 0);
		result += 17 * (result + (int)power);
		return result;
	}
	
	}