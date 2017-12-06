

abstract class Primary {
	
	void printParseTree(String indent) {
		IO.display(indent + indent.length() + " <primary>");
	}
	
	abstract void emitInstructions();

}