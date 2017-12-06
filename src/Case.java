
public class Case {
	
	Label label;
	SList sList1;
	
	Case(Label l, SList s1) {
		label = l;
		sList1 = s1;
	}
	
	Case(SList s) {
		sList1 = s;
	}
	
	void printParseTree(String indent) {

		String indent1 = indent + " ";

		if (label != null) {
//			IO.displayln(indent + indent.length() + " case : " + label.toString());
			IO.displayln(indent1 + indent1.length() + " case : " + label.toString());
			sList1.printParseTree(indent1+" ");
		}
		else {
			IO.displayln(indent1 + indent1.length() + " default");
			sList1.printParseTree(indent1+" ");
		}
	}
	
	void emitInstructions() {
		
		if (label != null) {

			IO.displayln("	push #1");
			
			int counter = Compiler.labelCounter++;
			IO.displayln("	push " + counter);
			IO.displayln("	eq");
			
			int nestedCounter = Compiler.labelCounter;
			
			IO.displayln("	ifF goto " + nestedCounter);
			
			sList1.emitInstructions();
			
			IO.displayln("	goto 1");
			IO.displayln(nestedCounter + ":");
			
		}
		
		else {
			
			sList1.emitInstructions();
		}
		
	}

}
