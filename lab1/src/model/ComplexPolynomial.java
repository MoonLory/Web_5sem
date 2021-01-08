package model;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the class of Complex Polynomial with some functions to work with it
 * @author Andrei Grishkin
 * @version 1.0
 */
public class ComplexPolynomial extends Polynomial {
	
	/**
	 * default constructor to create (0, 0) complex polynomial
	 */
	@SuppressWarnings("unchecked")
	public ComplexPolynomial() {
		super(0,new ArrayList<ComplexNumber>());
	}
	
	/**
	 * constructor of polynomial with given (n, coef) complex polynomial
	 * @param n degree of polynomial
	 * @param coef coefficients of polynomial
	 */
	@SuppressWarnings("unchecked")
	public ComplexPolynomial(int n, ArrayList<ComplexNumber> coef) {
		// TODO Auto-generated constructor stub
		super(n, coef);
	}
	
	/**
	 * multiplication of a complex polynomial by a constant
	 * @param p multiplication constant
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void multiplay(int p) {
		// TODO Auto-generated method stub
		ComplexNumber cn = new ComplexNumber(0.0, 0.0);
		for(int i = 0; i < this.getDegree(); i++) {
			cn.setRe(((ComplexNumber)this.getCoef(i)).getRe()*p);
			cn.setIm(((ComplexNumber)this.getCoef(i)).getIm()*p);
			this.setPolinomial(i, cn);
		}
	}
	
	/**
	 * subtraction of 2 polynomials
	 * @param p second complex polynomial
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void subtraction(Object p) {
		// TODO Auto-generated method stub
		ComplexNumber cn = new ComplexNumber(0.0, 0.0);
		if(this.checkPolinomial(this.getDegree(), (int)((Polynomial)p).getDegree())){
			for(int i = 0; i < (int)((Polynomial)p).getDegree(); i++) {
				this.setPolinomial(i, cn.subtract(((ComplexNumber)this.getCoef(i)), (ComplexNumber)((Polynomial)p).getCoef(i)));
			}
		}
		else {
			for(int i = 0; i < this.getDegree(); i++) {
				this.setPolinomial(i, cn.subtract(((ComplexNumber)this.getCoef(i)), (ComplexNumber)((Polynomial)p).getCoef(i)));
			}
			for(int i = this.getDegree(); i < (int)((Polynomial)p).getDegree(); i++) {
				this.addElemenInPolinomial((ComplexNumber)((Polynomial)p).getCoef(i));
			}
		}
	}
	
	/**
	 * addition of 2 polynomials
	 * @param p second complex polynomial
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void addition(Object p) {
		// TODO Auto-generated method stub
		ComplexNumber cn = new ComplexNumber(0.0, 0.0);
		if(this.checkPolinomial(this.getDegree(), (int)((Polynomial)p).getDegree())){
			for(int i = 0; i < (int)((Polynomial)p).getDegree(); i++) {
				this.setPolinomial(i, cn.addition(((ComplexNumber)this.getCoef(i)), (ComplexNumber)((Polynomial)p).getCoef(i)));
			}
		}
		else {
			for(int i = 0; i < this.getDegree(); i++) {
				this.setPolinomial(i, cn.addition(((ComplexNumber)this.getCoef(i)), (ComplexNumber)((Polynomial)p).getCoef(i)));
			}
			for(int i = this.getDegree(); i < (int)((Polynomial)p).getDegree(); i++) {
				this.addElemenInPolinomial((ComplexNumber)((Polynomial)p).getCoef(i));
			}
		}
	}
	
}
