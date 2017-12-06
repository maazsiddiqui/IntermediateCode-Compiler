import java.util.LinkedList;

public class BoolTerm extends BoolTermItem {

	LinkedList<BoolPrimaryItem> boolPrimaryItemList;

	BoolTerm(LinkedList<BoolPrimaryItem> bPItemList) {
		boolPrimaryItemList = bPItemList;
	}
	
	void printParseTree(String indent) {
			
		IO.displayln(indent + indent.length() + " <boolTerm>");
		for ( BoolPrimaryItem list : boolPrimaryItemList ) {					
			list.printParseTree(indent+" ");
		}	
	}

	void emitInstructions() {
		for ( BoolPrimaryItem list : boolPrimaryItemList ) {		
			list.emitInstructions();
		}
		
	}
}
