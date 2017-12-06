import java.util.LinkedList;

public class Expr {
	
	LinkedList<BoolTermItem> boolTermItemList;

	Expr(LinkedList<BoolTermItem> tItemList) {
		boolTermItemList = tItemList;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <Expr>");
		for ( BoolTermItem t : boolTermItemList )
			t.printParseTree(indent+" ");
	}
	
	void emitInstructions()
	{
		for ( BoolTermItem t : boolTermItemList )
			t.emitInstructions();
	}
	
	
}
