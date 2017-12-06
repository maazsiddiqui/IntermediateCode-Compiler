
public class AndBoolPrimaryItem extends BoolPrimaryItem {
	

	AndBoolPrimaryItem(BoolPrimary t) {
		boolPrimary = t;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " &&");
		boolPrimary.printParseTree(indent);
	}

	void emitInstructions() {
		boolPrimary.emitInstructions();
		IO.displayln("	and");
		
	}

}
