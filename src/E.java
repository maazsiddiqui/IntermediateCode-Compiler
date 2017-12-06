import java.util.*;

class E {
	
	LinkedList<TermItem> termItemList;

	E(LinkedList<TermItem> tItemList) {
		termItemList = tItemList;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <E>");
		for ( TermItem t : termItemList )
			t.printParseTree(indent+" ");
	}
	
	void emitInstructions()

	// Emit instructions to evaluate a sequence of terms operated by + or - using left associativity

	{
		for ( TermItem t : termItemList )
			t.emitInstructions();
	}

}