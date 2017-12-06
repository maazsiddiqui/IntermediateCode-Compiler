

class Assignment extends Statement
{
	String id; // variable on the left side of the assignment
	Expr expr;       // expression on the right side of the assignment

	Assignment(String s, Expr exp)
	{
		id = s;
		expr = exp;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		super.printParseTree(indent);		
		IO.displayln(indent1 + indent1.length() + " <assignment>");
		
		indent1 = indent1 + " ";
		
		IO.displayln(indent1 + indent1.length() + " " + id);
		IO.displayln(indent1 + indent1.length() + " =");
		expr.printParseTree(indent1);
	}
	
	void emitInstructions() {
		
		expr.emitInstructions();
		
		if ( !(Compiler.hmap.containsKey(id)) ) {
			Compiler.hmap.put(id, Compiler.indexCounter);
			Compiler.indexCounter += 1;
		}

		IO.displayln("	pop #" + Compiler.hmap.get(id));
		
	}
	

}