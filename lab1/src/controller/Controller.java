package controller;

import java.util.ArrayList;
import java.util.Arrays;

import model.ComplexPolynomial;
import model.RationalPolynomial;
import model.ComplexNumber;
import model.Polynomial;;

/**
 * Basic class demonstrates functions of Polynomial, RationalPolynomial, ComplexPolynomial and ComplexNumber classes
 * @author Andrei Grishkin
 * @version 1.0
 */
public class Controller {
	
	/**
     * Method to demonstrate work with Polynomial, RationalPolynomial, ComplexPolynomial and ComplexNumber classes
     */
	public void show() {
		
		RationalPolynomial rp = new RationalPolynomial(4,new ArrayList<Double>(Arrays.asList(1.0,4.0,6.0,8.0)));
		RationalPolynomial rp1 = new RationalPolynomial(3,new ArrayList<Double>(Arrays.asList(1.0,4.0,8.0)));
		rp.addition(rp1);
		System.out.println("Addition method for Rational Polynomial: "+rp.getAllCoef());
		
		rp = new RationalPolynomial(4,new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0,1.0)));
		rp1 = new RationalPolynomial(4,new ArrayList<Double>(Arrays.asList(1.0,6.0,3.0,4.0)));
		rp.subtraction(rp1);
		System.out.println("Subtraction method for Rational Polynomial: " + rp.getAllCoef());
		
		rp = new RationalPolynomial(4,new ArrayList<Double>(Arrays.asList(1.0,2.0,5.0,4.0)));
		rp.multiplay(3);
		System.out.println("Constant multiplication method for Rational Polynomial: " + rp.getAllCoef());
		
		ComplexPolynomial cp = new ComplexPolynomial(4,new ArrayList<ComplexNumber>(Arrays.asList(new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0))));
		ComplexPolynomial cp1 = new ComplexPolynomial(4,new ArrayList<ComplexNumber>(Arrays.asList(new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0))));
		cp.addition(cp1);
		System.out.println("Addition method for Complex Polynomial: "+cp.getAllCoef());
		
		cp = new ComplexPolynomial(4,new ArrayList<ComplexNumber>(Arrays.asList(new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0))));
		cp1 = new ComplexPolynomial(4,new ArrayList<ComplexNumber>(Arrays.asList(new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0))));
		cp.subtraction(cp1);
		System.out.println("Subtraction method for Complex Polynomial: "+cp.getAllCoef());
		
		cp = new ComplexPolynomial(4,new ArrayList<ComplexNumber>(Arrays.asList(new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0),new ComplexNumber(1.0,2.0))));
		cp.multiplay(10);
		System.out.println("Constant multiplication method for Complex Polynomial: "+cp.getAllCoef().toString());
		
		
		
		ArrayList<RationalPolynomial> pol = new ArrayList<RationalPolynomial>();
		pol.add(new RationalPolynomial(4,new ArrayList<Double>(Arrays.asList(1.0,2.0,3.0,4.0))));
		pol.add(new RationalPolynomial(4,new ArrayList<Double>(Arrays.asList(2.0,3.0,4.0,5.0))));
		pol.add(new RationalPolynomial(4,new ArrayList<Double>(Arrays.asList(3.0,4.0,5.0,6.0))));
		pol.add(new RationalPolynomial(4,new ArrayList<Double>(Arrays.asList(4.0,5.0,6.0,7.0))));
		
		sumPolinomial(pol);
	}
	
	/**
	 * Method to demonstrate sum of array polynomials
	 * @param pol ArrayList of polynomial
	 */
	public void sumPolinomial(ArrayList<RationalPolynomial> pol) {
		RationalPolynomial rp = new RationalPolynomial(1,new ArrayList<Double>(Arrays.asList(0.0)));
		for(int i = 0; i < pol.size(); i++) {
			rp.addition(pol.get(i));
		}
		System.out.println("Sum of polynomials: " + rp.getAllCoef());
	}
	
}
