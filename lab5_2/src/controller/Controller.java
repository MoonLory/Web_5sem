package controller;

import java.util.Scanner;

import model.client.Client;
import model.server.Server;

/**
 * This class demonstrate how work chat server
 * @author Grishkin Andrei
 * @version 1.0
 */
public class Controller {

	/**
	 * test method
	 */
	public static void test() {
		Scanner in = new Scanner(System.in);
		System.out.println("Choose a way to open a program (server, client): ");
		String choice = in.nextLine();
		if(choice.toLowerCase().compareTo("server")==0) {
			Server.main(null);
		}
		else {
			Client.main(null);
		}
	}

}
