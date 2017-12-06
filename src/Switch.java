
public class Switch extends Statement {
	
	Expr expr;
	CaseList caseList;
	
	Switch(Expr exp, CaseList cl) {
		expr = exp;
		caseList = cl;
	}
	
	void printParseTree(String indent) {
		
		String indent1 = indent + " ";
		
		super.printParseTree(indent);
		IO.displayln(indent1 + indent1.length() + " <switch>");
		
		indent1 = indent1 + " ";
		
		IO.displayln(indent1 + indent1.length() + " switch");
		expr.printParseTree(indent1);
		caseList.printParseTree(indent1);
	}
	
	void emitInstructions() {

		expr.emitInstructions();
		
		IO.displayln("	pop #" + Compiler.popCounter--);
		

		caseList.emitInstructions();
		
		IO.displayln("1:");
		
	}

}
