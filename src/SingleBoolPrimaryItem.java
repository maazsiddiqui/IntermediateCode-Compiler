
public class SingleBoolPrimaryItem extends BoolPrimaryItem {
	
	SingleBoolPrimaryItem(BoolPrimary t) {
		boolPrimary = t;
	}

	void printParseTree(String indent) {
		boolPrimary.printParseTree(indent);
	}

	void emitInstructions() {
		boolPrimary.emitInstructions();
	}
	
	

}
