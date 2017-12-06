import java.util.LinkedList;

public class CaseList {
	
	LinkedList<Case> caseList;

	CaseList(LinkedList<Case> cList) {
		caseList = cList;
	}

	void printParseTree(String indent) {
		IO.displayln(indent + indent.length() + " <case list>");
		for ( Case a : caseList )
			a.printParseTree(indent);
	}
	
	void emitInstructions() {
		
		Case temp = null;

		for ( Case a : caseList ) {
			
			if (a.label == null) {
				temp = a;
			}
			else {
				a.emitInstructions();
			}
		}
		
		if (temp != null ) temp.emitInstructions();
		
	}
}
