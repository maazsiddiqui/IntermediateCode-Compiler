
public class Print extends Statement {
	
	Expr expr;
	
	Print(Expr expr) {
		this.expr = expr;
	}
	
	void printParseTree(String indent) {

		String indent1 = indent + " ";
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <print>");
		expr.printParseTree(indent1+" ");
	}
	
	void emitInstructions() {
		
		expr.emitInstructions();
		IO.displayln("	print");
	}

}
