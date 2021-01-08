package controller;

import model.Car;
import model.CarPark;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class demonstrate how work treads 
 * @author Grishkin Andrei
 * @version 1.0
 */
public class Controller {
	
	public static final Logger logger = LogManager.getLogger();
	
	/**
	 * test method
	 */
	public static void test() {
		 CarPark station = new CarPark(10);
	     for (int i = 0; i < 10; i++) {
            new Car(1,5, station).start();
         }
	}
}
