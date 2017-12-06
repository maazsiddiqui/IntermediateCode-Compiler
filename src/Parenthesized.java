

class Parenthesized extends Primary
{
	Expr expr;

	Parenthesized(Expr expr) {
		this.expr = expr;
	}

	void printParseTree(String indent) {
		super.printParseTree(indent);
		IO.displayln("");
		expr.printParseTree(indent+" ");
	}
	
	void emitInstructions()
	{
		expr.emitInstructions();
	}

}