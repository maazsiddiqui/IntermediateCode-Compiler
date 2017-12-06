
public class Block extends Statement {
	
	SList sList;
	
	Block(SList sl) {
		sList = sl;
	}
	
	void printParseTree(String indent) {

		String indent1 = indent + " ";
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <block>");
		sList.printParseTree(indent1+" ");
		
		
	}
	
	void emitInstructions() {
		sList.emitInstructions();
	}

}
