/* Class: EmbeddedInteger */
/**
* This class allows its clients to treat an integer as an object.
* The underlying integer value is set using setValue and returned
* using getValue.
*/
import java.lang.*;
public class EmbeddedInteger{
	
	/* Constructor: EmbeddedInteger(n) */
	/**
	* Creates an embedded integer with the value n.
	*/
	public EmbeddedInteger(int n) {
		value = n;
	}
	
	/* Method: setValue(n) */
	/**
	* Sets the internal value of this EmbeddedInteger to n.
	*/
	public void setValue(int n) {
		value = n;
	} 
	
	/* Method: getValue() */
	/**
	* Returns the internal value of this EmbeddedInteger.
	*/
	public int getValue() {
		return value;
	}
	
	/* Method: toString() */
	/**
	* Overrides the toString method to make it return the stri
	* corresponding to the internal value.
	*/
	public String toString() {
		return Integer.toString(value);		
	}
	
	
	
	
	
	
	private int value;
}