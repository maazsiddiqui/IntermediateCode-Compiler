
public class BoolPrimary extends BoolPrimaryItem {
	
	E e1;
	RelOp relOp;
	E e2;
	
	public BoolPrimary(E e) {
		e1 = e;
	}
	
	BoolPrimary(E e1, RelOp relOp, E e2) {
		this.e1 = e1;
		this.relOp = relOp;
		this.e2 = e2;
	}
	
	void printParseTree(String indent) {
		
		String indent1 = indent + " ";
		
		IO.displayln(indent + indent.length() + " <boolPrimary>");
		e1.printParseTree(indent1);
		
		if (e2 != null) {
			
			IO.displayln(indent1 + indent1.length() + " " + relOp.toString());
			e2.printParseTree(indent1);
		}
	}
	
	void emitInstructions() {
		
		e1.emitInstructions();
		
		if (e2 != null) {

			e2.emitInstructions();
			relOp.emitInstructions();

		}
		
	}
	

}
