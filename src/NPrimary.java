
public class NPrimary extends Primary {
	
	Primary primary;
	
	NPrimary(Primary p) {
		primary = p;
	}
	
	void printParseTree(String indent) {
		super.printParseTree(indent);
		IO.displayln("");
		IO.displayln(indent + indent.length() + " -");
		primary.printParseTree(indent+" ");
	}

	void emitInstructions()
	{
		primary.emitInstructions();
		IO.displayln("	neg");
		
	}
	
}
