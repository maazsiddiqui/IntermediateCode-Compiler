
public class Label extends Int {
	
	String label;
	
	Label(int i) {
		super(i);
		label = Integer.toString(i);
	}
	
	public String toString(){
		return label;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <Label>");
		super.printParseTree(indent+" ");
	}
	
	void emitInstructions()
	{
		IO.displayln("FIX Label");
		
	}
	
}
