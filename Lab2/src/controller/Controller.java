package controller;

import java.util.ArrayList;

import model.Appliances;
import model.factory.DishwasherFactory;
import model.factory.Factory;
import model.factory.FridgeFactory;
import model.factory.BakeFactory;
import model.factory.HairdryerFactory;
import model.factory.ConditionerFactory;
import model.factory.VacuumCleanerFactory;

/**
 * Basic class demonstrates functions of classes
 * 
 * @author Andrei Grishkin
 * @version 1.0
 */
public class Controller {
	
	/**
	 * Method to demonstrate work with factory
	 */
	public void show() {
		Factory df = new DishwasherFactory();
		Factory ff = new FridgeFactory();
		Factory bf= new BakeFactory();
		Factory hf = new HairdryerFactory();
		Factory cf = new ConditionerFactory();
		Factory vf = new VacuumCleanerFactory();
		ArrayList<Appliances> alA = new ArrayList<>();
		ArrayList<Appliances> array = new ArrayList<>();
		alA.add(df.createAppliances());
		alA.add(ff.createAppliances());
		alA.add(bf.createAppliances());
		alA.add(hf.createAppliances());
		alA.add(cf.createAppliances());
		alA.add(vf.createAppliances());
		System.out.println("Factory method");
		for(int i = 0; i<alA.size();i++) {
			alA.get(i).review();
		}
	}
}
