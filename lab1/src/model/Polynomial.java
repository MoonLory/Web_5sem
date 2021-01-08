package model;

import java.util.ArrayList;
/**
 * 
 * @author Andrei Grishkin
 * @version 1.0
 *
 * This is the class of Polynomial with some functions to work with it
 *
 * @param <T> T type of ArrayList
 * @param <E> E type of polynomial
 */
public abstract class Polynomial<T,E> {
	
	/**
	 * pol ArrayList of polynomial component
	 */
	private ArrayList<T> pol;
	
	/**
	 * degree component of polynomial
	 */
	private int degree;
	
	/**
	 * constructor of polynomial with given (n, coef) polynomial
	 * @param n degree of polynomial
	 * @param coef coefficients of polynomial
	 */
	public Polynomial(int n, ArrayList<T> coef) {
		degree = n;
		pol=coef;
	}
	
	/**
	 * coef form ArrayList getter 
	 * @param i element index of polynomial
	 * @return element of ArrayList
	 */
	public T getCoef(int i) {
		return pol.get(i);
	}
	
	/**
	 * all coef form ArrayList getter 
	 * @return ArrayList
	 */
	public ArrayList<T> getAllCoef() {
		return pol;
	}
	
	/**
	 * degree getter
	 * @return degree of polynomial
	 */
	public int getDegree() {
		return degree;
	}
	
	/**
	 * polynomial element setter
	 * @param i element index of polynomial
	 * @param coef coefficient of polynomial
	 */
	public void setPolinomial(int i,T coef) {
		pol.set(i,coef);
	}
	
	/**
	 * add new element in polynomial
	 * @param coef coefficient of polynomial
	 */
	public void addElemenInPolinomial(T coef) {
		pol.add(coef);
		degree=pol.size();
	}
	
	/**
	 * Checking the size of 2 polynomials
	 * @param size1 size of first polynomial
	 * @param size2 size of second polynomial
	 * @return which has bigger size
	 */
	public boolean checkPolinomial(int size1,int size2)
	{
		return size1 >= size2 ? true : false;
	}
	
	/**
	 * multiplication of a polynomial by a constant
	 * @param p multiplication constant
	 */
	public abstract void multiplay(int p);
	
	/**
	 * subtraction of 2 polynomials
	 * @param p second polynomial
	 */
	public abstract void subtraction(E p);
	
	/**
	 * addition of 2 polynomials
	 * @param p second polynomial
	 */
	public abstract void addition(E p);
	
}
