
public class OrBoolTermItem extends BoolTermItem {
	
	OrBoolTermItem(BoolTerm t) {
		boolTerm = t;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " ||");
		boolTerm.printParseTree(indent);
	}

	void emitInstructions() {
		
		
		boolTerm.emitInstructions();
		IO.displayln("	or");
		
	}

}
