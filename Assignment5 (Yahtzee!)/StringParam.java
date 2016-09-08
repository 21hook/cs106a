import acm.program.*;


public class StringParam extends ConsoleProgram {

	public void run() {
		String str = "I need a value: ";
		
		addStr(str);
		
		println(str);
		
		str = updateStr(str);
		
		println(str);
		
		
		println("str + 5: " + str + 5);
		println("str: " + str);
	}
	
	private void addStr(String s) {
		s += "5";
	}
	
	private String updateStr(String s) {
		return s += "3";
	}

}