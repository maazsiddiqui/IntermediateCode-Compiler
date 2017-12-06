
public abstract class Statement {
	
	Statement statement;

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <statement>");
	}
	
	abstract void emitInstructions();


}
