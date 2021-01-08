package controller;

import java.util.ArrayList;

import model.Appliances;
import model.factory.ConditionerFactory;
import model.factory.Factory;
import model.factory.FridgeFactory;
import model.factory.BakeFactory;
import model.factory.DishwasherFactory;
import model.factory.HairdryerFactory;
import model.factory.VacuumCleanerFactory;
import model.manager.Manager;

/**
 * Basic class demonstrates functions of classes
 * 
 * @author Andrei Grishkin
 * @version 1.1
 */
public class Controller {
	
	/**
	 * Method to demonstrate work with factory
	 */
	public void show() {
		Manager manager = new Manager();
		Factory df = new ConditionerFactory();
		Factory ff = new FridgeFactory();
		Factory irf= new BakeFactory();
		Factory pf = new DishwasherFactory();
		Factory sf = new HairdryerFactory();
		Factory vcf = new VacuumCleanerFactory();
		ArrayList<Appliances> alA = new ArrayList<>();
		ArrayList<Appliances> array;
		alA.add(df.createAppliances());
		alA.add(ff.createAppliances());
		alA.add(irf.createAppliances());
		alA.add(pf.createAppliances());
		alA.add(sf.createAppliances());
		alA.add(vcf.createAppliances());
		System.out.println("Factory method all items");
		for (Appliances appliances : alA) {
			appliances.review();
		}
		System.out.println("-----------------------------------");
		System.out.println("Plug in socket");
		manager.plugInSocket(alA.get(0));
		alA.get(0).review();
		System.out.println("-----------------------------------");
		manager.printPower(alA);
		System.out.println("-----------------------------------");
		System.out.println("Select power");
		array = manager.findAppliancesPower(1, 2, alA);
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
