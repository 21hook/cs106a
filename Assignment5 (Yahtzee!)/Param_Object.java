/**
 * Param_Object.java
 * 
 * This program shows you ....
 */

import acm.program.*;

public class Param_Object extends ConsoleProgram {
	
	public void run() {
		EmbeddedInteger x = new EmbeddedInteger(17);
		increment(x);
		println(x);
	}
	
	private void increment(EmbeddedInteger n) {
		n.setValue(n.getValue() + 1);
		println(n);
	}
	
}

