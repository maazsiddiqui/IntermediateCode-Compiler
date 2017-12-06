
class Int extends Primary {
	
	int val;

	Int(int i) {
		val = i;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
		IO.displayln(" " + val);
		this.emitInstructions();
	}
	
	void emitInstructions()
	{
		IO.displayln("	push " + val);
//		Compiler.indexCounter++;
	}

}