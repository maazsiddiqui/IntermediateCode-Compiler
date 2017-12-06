
public class RelOp {
	
	String rO;
	
	public RelOp(String rO) {
		this.rO = rO;
	}
	
	public String toString() {
		return this.rO;
	}
	
	void printParseTree(String indent) {
//		super.printParseTree(indent);
		IO.displayln(" " + rO);
	}
	
	void emitInstructions() {
		
		switch(rO) {
        
		case "<" :
        	IO.displayln("	lt"); 
           break;
		case "<=" :
        	IO.displayln("	le"); 
           break;
		case ">" :
        	IO.displayln("	gt"); 
           break;
		case ">=" :
        	IO.displayln("	ge"); 
           break;
		case "==" :
        	IO.displayln("	eq"); 
           break;
		case "!=" :
        	IO.displayln("	neq"); 
           break;
        default :
           System.out.println("Invalid operation");
     }

	}

}
