package model;

/**
 * common resource for cars
 * @author Grishkin Andrei
 * @version 1.0
 */
public class ParkLot {
	
	/**
	 * lot id
	 */
	private int lotId;
	
	/**
	 * car
	 */
	Car car;
	
	/**
	 * constructor of lot
	 * @param lotId lot id
	 */
    public ParkLot(int lotId){
        this.lotId = lotId;
        this.car = null;
    }

    /**
     * Returns lot id of lot
     * @return lot id
     */
    public int getLotId() {
        return lotId;
    }
    
    /**
     * setter of lot id
     * @param lotId lot id
     */
    public void setLotId(int lotId) {
        this.lotId = lotId;
    }
    
    /**
     * Returns car
     * @return car
     */
    public Car getCar() {
        return car;
    }
    
    /**
     * setter of car
     * @param car car
     */
    public void setCar(Car car) {
        this.car = car;
    }
    
    /**
     * synchronized method lot check method
     * @return available lot or not
     */
    public boolean isAvailble() {
        if (car == null)
        	return true;
        return false;
    }
    
}
