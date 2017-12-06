import java.util.HashMap;

public abstract class Compiler extends Parser {
	
	static int indexCounter = 0;
	static int labelCounter = 1;
	static int popCounter = 1;
	static HashMap<String, Integer> hmap = new HashMap<String, Integer>();
			
	public static void main(String argv[])
	{
		// argv[0]: input file containing an assignment list
		// argv[1]: output file containing the compiled instruction stream
		
		setIO( argv[0], argv[1] );

		getToken();
		Statement statement = statement(); // build a parse tree
		
		if ( ! t.isEmpty() ) {
			displayln(t + " : Syntax Error, unexpected symbol where id expected");
		}
		else if ( ! errorFound ) {
			statement.emitInstructions();
		}

		closeIO();
	}
}