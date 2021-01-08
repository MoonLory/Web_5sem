package model;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the class of Rational Polynomial with some functions to work with it
 * @author Andrei Grishkin
 * @version 1.0
 */
public class RationalPolynomial extends Polynomial {
	
	/**
	 * default constructor to create (0, 0) rational polynomial
	 */
	@SuppressWarnings("unchecked")
	public RationalPolynomial() {
		super(0,new ArrayList<Double>());
	}
	
	/**
	 * constructor of polynomial with given (n, coef) rational polynomial
	 * @param n degree of polynomial
	 * @param coef coefficients of polynomial
	 */
	@SuppressWarnings("unchecked")
	public RationalPolynomial(int n, ArrayList<Double> coef) {
		super(n,coef);
	}
	
	/**
	 * multiplication of a rational polynomial by a constant
	 * @param p multiplication constant
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void multiplay(int p) {
		// TODO Auto-generated method stub
		for(int i = 0; i < this.getDegree(); i++) {
			this.setPolinomial(i, (Double)this.getCoef(i) * p);
		}
	}
	
	/**
	 * subtraction of 2 polynomials
	 * @param p second rational polynomial
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void subtraction(Object p)  {
		// TODO Auto-generated method stub
		if(this.checkPolinomial(this.getDegree(), (int)((Polynomial)p).getDegree())){
			for(int i = 0; i < (int)((Polynomial)p).getDegree(); i++) {
				this.setPolinomial(i, (Double)this.getCoef(i) - (Double)((Polynomial)p).getCoef(i));
			}
		}
		else {
			for(int i = 0; i < this.getDegree(); i++) {
				this.setPolinomial(i, (Double)this.getCoef(i) - (Double)((Polynomial)p).getCoef(i));
			}
			for(int i = this.getDegree(); i < (int)((Polynomial)p).getDegree(); i++) {
				this.addElemenInPolinomial((Double)((Polynomial)p).getCoef(i)*(-1));
			}
		}
	}
	
	/**
	 * addition of 2 polynomials
	 * @param p second rational polynomial
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addition(Object p) {
		// TODO Auto-generated method stub
		if(this.checkPolinomial(this.getDegree(), (int)((Polynomial)p).getDegree())){
			for(int i = 0; i < (int)((Polynomial)p).getDegree(); i++) {
				this.setPolinomial(i, (Double)this.getCoef(i) + (Double)((Polynomial)p).getCoef(i));
			}
		}
		else {
			for(int i = 0; i < this.getDegree(); i++) {
				this.setPolinomial(i, (Double)this.getCoef(i) + (Double)((Polynomial)p).getCoef(i));
			}
			for(int i = this.getDegree(); i < (int)((Polynomial)p).getDegree(); i++) {
				this.addElemenInPolinomial((Double)((Polynomial)p).getCoef(i));
			}
		}
		
	}


	

	

}
