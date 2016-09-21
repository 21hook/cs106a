/*
	Diagrams.java
	
	This program shows ...
*/
import acm.program.*;

public class Diagrams extends ConsoleProgram {
	
	public void run() {
		 Rational r = new Rational(1, 2); 
		 r = raiseToPower(r, 3);
		 println("r ^ 3 = " + r);
	}
	
	private Rational raiseToPower(Rational x, int n) {
		 Rational result = new Rational(1);
		 
		 for (int i = 0; i < n; i++) {
			 result = result.multiply(x);
		 } // `result` variable in the heap are garbage at this point in the calculation.
		 return result;
	}

}	