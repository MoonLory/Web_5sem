package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import controller.Controller;
import model.exception.CarParkWorkException;

/**
 * This class manages the parking lot.
 * @author Grishkin Andrei
 * @version 1.0
 */
public class CarPark {
	
	/**
	 * park size
	 */
	private int parkSize;
	
	/**
	 * parking lot
	 */
    private CopyOnWriteArrayList<ParkLot> lots;
    
    /**
     * critical section
     */
    Lock cs;
    
    /**
     * semaphor
     */
    Semaphore semaphore;
    
    /**
     * Constructor to create parking lot with lots
     * @param parkSize size of parking lot
     */
    public CarPark(int parkSize){
        this.parkSize = parkSize;
        cs = new ReentrantLock();
        semaphore = new Semaphore(parkSize);
        this.lots = new CopyOnWriteArrayList<ParkLot>();
        for (int i = 0; i < parkSize; i++) {
        	lots.add(new ParkLot(i));
		}
        Controller.logger.info("parking lot initialized");
    }
    
    /**
     * synchronized method to add car to parking lot
     * @param car car
     * @return returns information whether there is free lot
     */
    public int findFreeParkLots(Car car) throws CarParkWorkException {
    	cs.lock();
    	if (lots.size()==0)
    		throw new CarParkWorkException("Lots size = 0"); 
    	for (int i = 0; i < lots.size(); i++){
    		if (lots.get(i).isAvailble()) {
    			lots.get(i).setCar(car);
    			car.spaceIndex = i;
    			cs.unlock();
    			return i;
			}
        }
    	cs.unlock();
    	return -1;
    }
    
    /**
     * swap car 
     * @param car car
     */
    public void swapPosition(Car car) {
		cs.lock();
		int pl = car.spaceIndex;
		int index;
		ArrayList<Integer> ar = new ArrayList<>();
		for (int i = 0; i < parkSize; i++) {
			if(lots.get(i).isAvailble()) {
				ar.add(i);
			}
		}
		if(ar.size()<=0)
			return;
		Random r = new Random();
		int number = Math.abs(r.nextInt(ar.size()));
		
		index = ar.get(number);
		
		Car newCar = lots.get(index).getCar();
		int newIndex = lots.get(index).getLotId();
		lots.get(pl).setCar(newCar);
		lots.get(newIndex).setCar(car);
		car.setSpaceIndex(newIndex);
		cs.unlock();
    }
    
    /**
     * synchronized method to take lot
     * @param car car
     */
    public void takeLot(Car car) {
    	cs.lock();
	    try {
	    	int space = findFreeParkLots(car);
	        if (space == -1) {
	            Controller.logger.info(car.getName() + " Is waiting for free spaces in parking");
	            cs.unlock();
	            if (semaphore.tryAcquire(car.getWaitTime())) {
	            	cs.lock();
	            	space = findFreeParkLots(car);
	            	cs.unlock();
	            }
	            cs.lock();
	            if (space == -1) {
	            	car.spaceIndex = -1;
	                Controller.logger.info(car.getName() + " left the park because time is up");
	            }
	            else{
	            	Random r = new Random();
		        	int number = r.nextInt()%2;
		        	if(number==1) {
		        		swapPosition(car);
		        		Controller.logger.info(car.getName() + " Swap position");
		        	}
	                Controller.logger.info(car.getName() + " Found place after waiting, Place #" + space);  
	            }
	        }
	        else{
	        	Random r = new Random();
	        	int number = r.nextInt()%2;
	        	if(number==1) {
	        		swapPosition(car);
	        		Controller.logger.info(car.getName() + " Swap position");
	        	}
	            Controller.logger.info(car.getName() + " Found place, Place #" + space);
	        }
	    }
        catch (CarParkWorkException e){
            Controller.logger.error(e.getMessage());
        }
	    finally {
			cs.unlock();
		}
    }
    
    /**
     * Leave from parking lot
     * @param car car
     */
    public void LeaveLot(Car car) {
    	cs.lock();
    	lots.get(car.getSpaceIndex()).setCar(null);
    	semaphore.release();
        cs.unlock();
    }
}
