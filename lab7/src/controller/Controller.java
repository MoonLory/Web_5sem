package controller;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import XML.AppliancesParser;
import XML.DOMParser;
import XML.SAXParser;
import XML.StAXParser;
import XML.XMLValidator;
import exception.XMLValidatorException;
import model.Appliances;
import model.factory.BakeFactory;
import model.factory.Factory;
import model.factory.FridgeFactory;
import model.factory.ConditionerFactory;
import model.factory.HairdryerFactory;
import model.factory.DishwasherFactory;
import model.factory.VacuumCleanerFactory;
import model.manager.Manager;

/**
 * Basic class demonstrates functions of classes
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class Controller {
	
	public static final Logger logger = LogManager.getLogger();
	
	/**
     * Validates XML file with given schema
     * @param file XML file
     * @param schema XSD file
     * @return true if validation succeed, otherwise false
	 * @throws XMLValidatorException 
     */
    public static boolean validate(String file, String schema) {
        boolean result = false;
		try {
			result = XMLValidator.validate(file, schema);
		} catch (XMLValidatorException e) {
			e.printStackTrace();
		}
        if (!result){
        	logger.info("Validation failed");
        }
        else {
        	logger.info("Validation succeed");
        }
        return result;
    }
	
	/**
	 * Method to demonstrate work with factory
	 */
	public void show() {
		if (!validate("appliances.xml", "xsd_schema.xsd")){
            logger.info("Validation failed");;
            System.exit(1);
        }
		Manager manager = new Manager();

		ArrayList<Appliances> alA = new ArrayList<>();
		ArrayList<Appliances> array = new ArrayList<>();
		Scanner in = new Scanner(System.in);
        System.out.println("Choose parser:\n" +
                "1 - DOM\n" +
                "2 - SAX\n" +
                "3 - StAX");
        AppliancesParser parser = null;
        int choice;
        while (true){
            choice = in.nextInt();
            switch (choice){
                case 1:
                    parser = new DOMParser();
                    break;
                case 2:
                    parser = new SAXParser();
                    break;
                case 3:
                    parser = new StAXParser();
                    break;
                default:
                    System.out.println("Incorrect choice. Try again.");
                    logger.info("user enter incorrect param(" + choice + ")");;
                    break;
            }
            if (parser != null){
                break;
            }
        }
        try {
            alA = parser.parse("appliances.xml");
        } catch (Exception e) {
            e.printStackTrace();
        }
		System.out.println("Factory method");
		for(int i = 0; i<alA.size();i++) {
			alA.get(i).review();
		}
		System.out.println("-----------------------------------");
		System.out.println("Plug in socket");
		manager.plugInSocket(alA.get(0));
		alA.get(0).review();
		System.out.println("-----------------------------------");
		System.out.println("Select power");
		array = manager.findAppliancesPower(10, 70, alA);
		manager.printArray(array);
		System.out.println("-----------------------------------");
		System.out.println("Select true socket");
		array = manager.findAppliancesSocket(true, alA);
		manager.printArray(array);
		System.out.println("-----------------------------------");
		System.out.println("Sort by power");
		array = manager.sortByPower(alA);
		manager.printArray(array);
		System.out.println("-----------------------------------");
		System.out.println("Sort by socket");
		array = manager.sortBySocket(alA);
		manager.printArray(array);
		System.out.println("-----------------------------------");
	}
	
}
