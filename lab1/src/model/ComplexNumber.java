package model;

/**
 * This is the class of Complex Number with some functions to work with it
 * @author Andrei Grishkin
 * @version 1.0
 */
public class ComplexNumber {
	
	/**
	 * "re" component of complex number
	 */
	private Double re;
	
	/**
	 * "im" component of complex number
	 */
	private Double im;
	
	/**
	 * default constructor to create (0, 0) complex number
	 */
	public ComplexNumber() {
		this.re = 0.0;
		this.im = 0.0;
	}
	
	/**
	 * constructor of complex number with given (re) number
	 * @param re re component of complex number
	 */
	public ComplexNumber(Double re) {
		this.re = re;
		this.im = 0.0;
	}
	
	/**
	 * constructor of complex number with given (re, im) number
	 * @param re re component of complex number
	 * @param im im component of complex number
	 */
	public ComplexNumber(Double re,Double im) {
		this.re = re;
		this.im = im;
	}
	
	/**
	 * re component getter
	 * @return re component of complex number
	 */
	public Double getRe()
	{
		return re;
	}
	
	/**
	 * im component getter
	 * @return im component of complex number
	 */
	public Double getIm()
	{
		return im;
	}
	
	/**
	 * re component setter
	 * @param re component of complex number
	 */
	public void setRe(Double re) {
		this.re = re;
	}
	
	/**
	 * im component setter
	 * @param im component of complex number
	 */
	public void setIm(Double im) {
		this.im = im;
	}
	
	/**
	 * Returns the sum of 2 complex numbers
	 * @param cn1 first complex number
	 * @param cn2 second complex number
	 * @return sum of 2 complex numbers
	 */
	public ComplexNumber addition(ComplexNumber cn1,ComplexNumber cn2)
	{
		return new ComplexNumber(cn1.getRe() + cn2.getRe(), cn1.getIm() + cn2.getIm());
	}
	
	/**
	 * Returns the difference of 2 complex numbers
	 * @param cn1 first complex number
	 * @param cn2 second complex number
	 * @return difference of 2 complex numbers
	 */
	public ComplexNumber subtract(ComplexNumber cn1, ComplexNumber cn2) {
        return new ComplexNumber(cn1.getRe() - cn2.getRe(), cn1.getIm() - cn2.getIm());
    }
	
	/**
	 * Converts complex number to string
	 * @return complex number as string
	 */
	@Override
	public String toString() {
		String str = this.re + " " + this.im;
		return str;
	} 
	
}
