
class AddTermItem extends TermItem

// Represents "+ <term>"

{
	// Term term; inherited from TermItem

	AddTermItem(Term t)
	{
		term = t;
	}

	void printParseTree(String indent)
	{
		IO.displayln(indent + indent.length() + " +");
		term.printParseTree(indent);
	}
	
	void emitInstructions()
	{
		term.emitInstructions();
		IO.displayln("	add");
		
//		IO.displayln("\tpush #" + Compiler.hmap.get());		
	}


}