package view;

import controller.Controller;

/**
 * Main class which run controller's demonstrate method
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public class Main {
	
	/**
	 * The Main method
	 * 
	 * @param args command line parameters
	 */
	public static void main(String[] args) {
		Controller controller = new Controller();
		controller.show();
	}

}
