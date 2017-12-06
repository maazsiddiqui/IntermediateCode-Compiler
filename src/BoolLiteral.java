
public class BoolLiteral extends Primary {
	
	String boolLiteral;
	
	public BoolLiteral(String bl) {
		boolLiteral = bl;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		IO.displayln(" " + boolLiteral);
	}
	
	void emitInstructions()
	{
		IO.displayln("	push  " + boolLiteral);
	}
	
}
