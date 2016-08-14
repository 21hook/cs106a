/*
 Fibonacci.java
 
 This program is to print out the Fibonacci Sequence in the specific range.
*/

import acm.program.*;

public class Fibonacci  extends ConsoleProgram {

	private static final int MAX_TERM_VALUE = 10000;
	
	public void  run()
	{
		int n_2, n_1; // preceding two terms of a Sequence
		int n; // a current term of a sequence
		
		println("This program lists the the Fibonacci Sequence.");
		println((n_2=0));
		println((n_1=1));
		
		while((n = n_2 + n_1) < MAX_TERM_VALUE) {
			println(n);
			n_2 = n_1;
			n_1 = n;
		}
		
		
	}

}