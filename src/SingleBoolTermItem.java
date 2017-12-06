
public class SingleBoolTermItem extends BoolTermItem {
	
	SingleBoolTermItem(BoolTerm t) {
		boolTerm = t;
	}

	void printParseTree(String indent) {
		boolTerm.printParseTree(indent);
	}

	void emitInstructions() {
		boolTerm.emitInstructions();
	}

}
