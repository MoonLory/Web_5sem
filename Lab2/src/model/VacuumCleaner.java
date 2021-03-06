package model;
/**
 * A machine for cleaning dust and dirt from surfaces due to suction by air flow
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class VacuumCleaner extends Appliances{
	
	/**
	 * volume suction of the vacuum cleaner
	 */
	private double volumeSuction;
	
	/**
	 * Constructor, creates vacuum cleaner with name, power, voltage and volume suction
	 * 
	 * @param name name of the vacuum cleaner
	 * @param powerWt power of the vacuum cleaner
	 * @param volumeSuction volume suction of the vacuum cleaner
	 */
	public VacuumCleaner(String name, double powerWt, double volumeSuction) {
		// TODO Auto-generated constructor stub
		super(name, powerWt);
		this.volumeSuction = volumeSuction;
	}
	
	/**
	 * getter of volume suction
	 * 
	 * @return volume suction
	 */
	public double getVolumeSuction() {
		return volumeSuction;
	}
	
	/**
	 * volume suction setter
	 * 
	 * @param volumeSuction volume suction of the vacuum cleaner
	 */
	public void setVolumeSuction(double volumeSuction) {
		this.volumeSuction = volumeSuction;
	}
	
	@Override
	public void review() {
		// TODO Auto-generated method stub
		String str = String.format("The vacuum cleaner has power = %f and volume suction = %f and vacuum cleaner plugged in = %s",this.getPower(),this.volumeSuction, this.getvoltage() ? "true" : "false");
		System.out.println(str);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) return false;
		
		Appliances appl = (Appliances) obj;
		VacuumCleaner vc = (VacuumCleaner) obj;
		
		return appl.equals(this) && volumeSuction == vc.volumeSuction;
	}
	
	@Override
	public String toString() {
		String stringFromSuper = super.toString();
		return String.format("%s\nvolumeSuction: %2f", stringFromSuper, this.volumeSuction);
	}
	
	@Override
	public int hashCode() {
		int hashFromSuper = super.hashCode();
		return 77 * (((int)volumeSuction+1)*17 + hashFromSuper*21);
	}

}
