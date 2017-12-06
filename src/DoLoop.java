
public class DoLoop extends Statement {

	Statement statement;
	Expr expr;
	
	DoLoop(Statement s, Expr exp) {
		statement = s;
		expr = exp;
	}
	
	void printParseTree(String indent) {

		String indent1 = indent + " ";
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <do loop>");
		
		indent1 = indent1 + " ";
		
		IO.displayln(indent1 + indent1.length() + " do");
		statement.printParseTree(indent1);
		IO.displayln(indent1 + indent1.length() + " while");
		expr.printParseTree(indent1);
	}
	
	void emitInstructions() {
		
		int counter = Compiler.labelCounter++;

		IO.displayln(counter + ":");

		statement.emitInstructions();
		expr.emitInstructions();
		
		int nestedCounter = Compiler.labelCounter++;

		IO.displayln("	ifF goto " + nestedCounter);

		IO.displayln("	goto " + counter);
		IO.displayln(nestedCounter + ":");
	}
}
