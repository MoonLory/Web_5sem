package model;

import java.awt.List;
import java.util.ArrayList;
import java.util.concurrent.ThreadPoolExecutor;

import controller.Controller;
import model.exception.CarParkWorkException;

/**
 * Class image cars
 * @author Grishkin Andrei
 * @version 1.0
 */
public class Car extends Thread {
	
	/**
	 * wait time
	 */
    int waitTime;
    
    /**
     * parked time
     */
    int parkedTime;
    
    /**
     * space index
     */
    int spaceIndex;
    
    /**
     * parking
     */
    CarPark parking;
    
    /**
     * Constructor to create client with specified name and problem
     * @param name of client
     * @param complexity of problem
     */
    /**
     * Constructor to create cars with waitTime, parkedTime and Car Park
     * @param waitTime wait time
     * @param parkedTime parked time
     * @param parking parking
     */
    public Car(int waitTime, int parkedTime, CarPark parking){
        this.waitTime = waitTime;
        this.parkedTime = parkedTime;
        spaceIndex = -1;
        this.parking = parking;
    }
    
    /**
     * Returns name of client
     * @return name of client
     */
    
    /**
     * Returns wait time
     * @return wait time
     */
    int getWaitTime() {
    	return waitTime;
    }
    
    /**
     * Returns parked time
     * @return parked time
     */
    int getParkedTime() {
    	return parkedTime;
    }
    
    /**
     * Returns space index
     * @return space index
     */
    int getSpaceIndex() {
    	return spaceIndex;
    }

    /**
     * setter of space index
     * @param index space index
     */
    void setSpaceIndex(int index) {
    	this.spaceIndex = index;
    }
    
    
    /**
     * Method in which the car tries to park
     */
    public synchronized void run() {
        String currentThreadName = Thread.currentThread().getName();
        Controller.logger.info(currentThreadName + " Entering parking");
		parking.takeLot(this);

        boolean wentToParking = false;
        if(spaceIndex != -1) {
            try {
                wentToParking = true;
                Controller.logger.info(currentThreadName + " Entered parking successfully");
                Thread.sleep(parkedTime);
                Controller.logger.info(currentThreadName + " Leaving parking");
            } catch (InterruptedException e) {
            	e.getMessage();
                Controller.logger.error(currentThreadName + " Some problems with going to walk: " + e.getMessage());
            }
            if(wentToParking) {
            	parking.LeaveLot(this);
                Controller.logger.info(currentThreadName + " Left parking");
            }
        }
        else{
            Controller.logger.info(currentThreadName + " Found no free space and left parking");
        }
    }

	@Override
	public String toString() {
		String currentThreadName = Thread.currentThread().getName();
		return "Thread number: " + currentThreadName
	            + " waitTime: " + waitTime
	            + " parkedTime:" + parkedTime
	            + "\n";
	}
}
