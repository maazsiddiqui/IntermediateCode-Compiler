
public class WhileLoop extends Statement {
	
	Expr expr;
	Statement statement;
	
	WhileLoop(Expr exp, Statement s) {
		expr = exp;
		statement = s;
	}

	void printParseTree(String indent) {

		String indent1 = indent + " ";
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <while loop>");
		
		indent1 = indent1 + " ";
		
		IO.displayln(indent1 + indent1.length() + " while");
		expr.printParseTree(indent1);
		statement.printParseTree(indent1);
	}
	
	void emitInstructions() {

		int counter = Compiler.labelCounter++;

		IO.displayln(counter + ":");
	
		expr.emitInstructions();
		
		int nestedCounter = Compiler.labelCounter++;

		
		IO.displayln("	ifF goto " + nestedCounter);
		
		statement.emitInstructions();
		
		IO.displayln("	goto " + counter);
		IO.displayln(nestedCounter + ":");
		
		
	}
}
