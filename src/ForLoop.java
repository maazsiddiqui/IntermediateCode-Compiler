
public class ForLoop extends Statement {
	
	Assign assign1;
	Expr expr;
	Assign assign2;
	Statement statement;
	
	ForLoop(Assign a1, Expr exp, Assign a2, Statement s) {
		assign1 = a1;
		expr = exp;
		assign2 = a2;
		statement = s;
	}
	
	void printParseTree(String indent) {

		String indent1 = indent + " ";
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <for loop>");
		
		indent1 = indent1 + " ";
		
		IO.displayln(indent1 + indent1.length() + " for");
		assign1.printParseTree(indent1);
		expr.printParseTree(indent1);
		assign2.printParseTree(indent1);
		statement.printParseTree(indent1);
	}

	void emitInstructions() {
		
		assign1.emitInstructions();

		int counter = Compiler.labelCounter++;

		IO.displayln(counter + ":");

		expr.emitInstructions();
		
		int nestedCounter = Compiler.labelCounter++;

		IO.displayln("	ifF goto " + nestedCounter);
		
		statement.emitInstructions();
		
		assign2.emitInstructions();
		
		IO.displayln("	goto " + counter);
		IO.displayln(nestedCounter + ":");
	
	}
	
}
