import java.util.LinkedList;

public class SList {
	
	LinkedList<Statement> sList;

	SList(LinkedList<Statement> al) {
		sList = al;
	}

	void printParseTree(String indent) {
		
		IO.displayln(indent + indent.length() + " <s list>");
		
		for ( Statement a : sList ) {
			
			a.printParseTree(indent+" ");
		}
	}

	void emitInstructions() {
		
		for ( Statement a : sList ) {
			
			a.emitInstructions();
		}
		
	}

}
