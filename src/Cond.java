
public class Cond extends Statement {
	
	Expr expr;
	Statement statement1;
	Statement statement2;
	
	Cond(Expr exp, Statement s1, Statement s2) {
		expr = exp;
		statement1 = s1;
		statement2 = s2;
	}

	void printParseTree(String indent) {
		
		String indent1 = indent + " ";
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <cond>");
		
		indent1 = indent1 + " ";
		
		IO.displayln(indent1 + indent1.length() + " if");
		expr.printParseTree(indent1);
		statement1.printParseTree(indent1);
		
		if (statement2 != null) {
			IO.displayln(indent1 + indent1.length() + " else");
			statement2.printParseTree(indent1);
		}
	}
	
	void emitInstructions() {
		
		expr.emitInstructions();
		
		int counter = Compiler.labelCounter;
		Compiler.labelCounter++;
		IO.displayln("	ifF goto " + counter);
		
		statement1.emitInstructions();

		
		if (statement2 != null) {
			
			int nestedCounter = Compiler.labelCounter;
			Compiler.labelCounter++;
			
			IO.displayln("	goto " + nestedCounter);
			
			IO.displayln(counter + ":");
			
			statement2.emitInstructions();

			IO.displayln(nestedCounter + (":"));

		}
		
		else {
			IO.displayln(counter + ":");
		}

	}

}
