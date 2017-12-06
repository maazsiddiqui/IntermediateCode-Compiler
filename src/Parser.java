/**

This class is a top-down, recursive-descent parser for the following syntactic categories:

<statement> --> <assignment> | <cond> | <switch> | <while loop> | <do loop> | <for loop> | <print> | <block> 
<assignment> --> <id> "=" <expr> ";" 
<cond> --> "if" "(" <expr> ")" <statement> [ "else" <statement> ] 
<switch> --> "switch" "(" <expr> ")" "{" <case list> "}" 
<case list> --> { <case> }+ 
<case> --> "case" <label> ":" <s list> | "default" ":" <s list> 
<label> --> <int> 
<while loop> --> "while" "(" <expr> ")" <statement> 
<do loop> --> "do" <statement> "while" "(" <expr> ")" ";" 
<for loop> --> "for" "(" <assign> ";" <expr> ";" <assign> ")" <statement> 
<assign> --> <id> "=" <expr> 
<print> --> "print" <expr> ";" 
<block> --> "{" <s list> "}" 
<s list> --> { <statement> } 
<expr> --> <boolTerm> { "||" <boolTerm> } 
<boolTerm> --> <boolPrimary> { "&&" <boolPrimary> } 
<boolPrimary> --> <E> [ <rel op> <E> ] 
<rel op> --> "<" | "<=" | ">" | ">=" | "==" | "!=" 
<E> --> <term> { (+|-) <term> } 
<term> --> <primary> { (*|/) <primary> } 
<primary> --> <id> | <int> | <float> | <floatE> | <boolLiteral> | "(" <expr> ")" | - <primary> | ! <primary> 
<boolLiteral> --> "false" | "true"

Note: The binary operators +, -, *, / associate to left.

The definitions of the tokens are given in the lexical analyzer class file "LexArith.java". 

The following variables/functions of "IO"/"LexArith" classes are used:

static void display(String s)
static void displayln(String s)
static void setIO(String inFile, String outFile)
static void closeIO()

static String t // holds an extracted token
static State state // the current state of the finite automaton
static int getToken() // extracts the next token

An explicit parse tree is constructed in the form of nested class objects.
 
The main function will display the parse tree in linearly indented form.
Each syntactic category name labeling a node is displayed on a separate line, 
prefixed with the integer i representing the node's depth and indented by i blanks.

All iterations of the form { ... } and { ... }+ are implemented by java.util.LinkedList.
Function call list.add(x) appends element x to the end of list. 

**/

import java.util.*;

public abstract class Parser extends LexArith {
	
	static boolean errorFound = false;
	
	public static Statement statement() {
		
	// <statement> --> <assignment> | <cond> | <switch> | <while loop> | <do loop> | <for loop> | <print> | <block> 
		
		// for <assignment>
		if ( state == State.Id ) {
			String id = t;
			getToken();
			if ( state == State.Assign ) {
				getToken();
				Expr expr = expr();
				if ( state == State.Semicolon ) {
					getToken();
					return new Assignment(id, expr);
				}
				else {
					errorMsg(4);
				}
			}
			else {
				errorMsg(3);
			}
		}

		// for <cond>
		else if (state == State.Keyword_if) {
			getToken(); // check for "("
			if (state == State.LParen) {
				getToken();
				Expr e1 = expr();
				if (state == State.RParen) {
					getToken();
					Statement s1 = statement();
					if (state == State.Keyword_else) {
						getToken();
						Statement s2 = statement();
						return new Cond(e1, s1, s2);
					}
					else {
						return new Cond(e1, s1, null);
					}
				}
				else {
					
					errorMsg(6);
					return null;
				}
			}
			else {
				errorMsg(7);
				return null;
			}
		}
		
		// for <switch>
		else if (state == State.Keyword_switch) {
			getToken(); // check for "("
			if (state == State.LParen) {
				getToken();
				Expr expr = expr();
				if (state == State.RParen) {
					getToken();
					if (state == State.LBrace) {
						getToken();
						CaseList cList = caseList();
						if (state == State.RBrace) {
							getToken();
							return new Switch(expr, cList);
						}
						else {
							errorMsg(9);
							return null;
						}
					}
					else {
						errorMsg(8);
						return null;
					}
				}
				else {
					errorMsg(6);
					return null;
				}
			}
			else {
				errorMsg(7);
				return null;
			}
		}
		
		// for <while loop>
		else if (state == State.Keyword_while) {
			getToken(); // check for "("
			if (state == State.LParen) {
				getToken();
				Expr expr = expr();
				if (state == State.RParen) {
					getToken();
					Statement s = statement();
					return new WhileLoop(expr, s);
				}
				else {
					errorMsg(6);
					return null;
				}
			}
			else {
				errorMsg(7);
				return null;
			}	
		}
		
		// for <do loop>
		else if (state == State.Keyword_do) {
			getToken();
			Statement s = statement();
			if(state == State.Keyword_while) {
				getToken(); // check for "("
				if (state == State.LParen) {
					getToken();
					Expr expr = expr();
					if (state == State.RParen) {
						getToken();
						if ( state == State.Semicolon ) {
							getToken();
							return new DoLoop(s, expr);
						}
						else {
								errorMsg(4);
						}				
					}
					else {
						errorMsg(6);
						return null;
					}
				}
				else {
					errorMsg(7);
					return null;
				}		
			}
			else {
				errorMsg(10);
				return null;
			}
		}
		
		// for <for loop>
		else if (state == State.Keyword_for) {
			getToken();
			if (state == State.LParen) {
				getToken();
				Assign a1 = assign();
				if (state == State.Semicolon) {
					getToken();
					Expr expr = expr();
					if(state == State.Semicolon) {
						getToken();
						Assign a2 = assign();
						if(state == State.RParen) {
							getToken();
							Statement s1 = statement();
							return new ForLoop(a1, expr, a2, s1);
						}
						else {
							errorMsg(6);
							return null;
						}
					}
					else {
						errorMsg(4);
						return null;
					}
				}
				else {
					errorMsg(4);
					return null;
				}
			}
			else {
				errorMsg(7);
				return null;
			}
		
		
		
		}
		
		// for <print>
		else if (state == State.Keyword_print) {
			getToken();
			Expr expr = expr();
			if(state == State.Semicolon) {
				getToken();
				return new Print(expr);
			}
			else {
				errorMsg(4);
				return null;
			}
		}
		
		// for <block>
		else if (state == State.LBrace) {
			getToken();
			SList sList = sList();
			if (state == State.RBrace) {
				getToken();
				return new Block(sList);
			}
			else {
				errorMsg(9);
				return null;
			}
		}	
		return null;			
	}

	public static CaseList caseList() {
		
	// <case list> --> { <case> }+ 
		
		LinkedList<Case> caseList = new LinkedList<Case>();
		
		Case case_ = case_();
		caseList.add(case_);
		while ( state == State.Keyword_case || state == State.Keyword_default) {
			case_ = case_();
			caseList.add(case_); // append "case" to the end of "caseList"
		}
		return new CaseList(caseList);
	}
	
	public static Case case_() {
		
	// <case> --> "case" <label> ":" <s list> | "default" ":" <s list>
		
		if (state == State.Keyword_case) {
			getToken();
			Label label = label();
			if (state == State.Colon) {
				getToken();
				SList sList = sList();
				return new Case(label, sList);
			}
			else {
				errorMsg(13);
				return null;
			}
		}
		else if (state == State.Keyword_default) {
			getToken();
			if (state == State.Colon) {
				getToken();
				SList sList = sList();
				return new Case(sList);
			}
			else {
				errorMsg(13);
				return null;
			}
		}
		else {
			errorMsg(12);
			return null;
		}
	}
	
	private static Label label() {
		
	// <label> --> <int>
		
		if (state == State.Int) {
			Label label = new Label(Integer.parseInt(t));
			getToken();
			return label;
		}
		else {
			errorMsg(2);
			return null;
		}
	}

	private static Assign assign() {
	
	// <assign> --> <id> "=" <expr>
		
		if ( state == State.Id ) {
			String id = t;
			getToken();
			if ( state == State.Assign ) {
				getToken();
				Expr expr = expr();
				return new Assign(id, expr);
			}
			else {
					errorMsg(3);
					return null;
			}
		}
		else {
				errorMsg(2);
				return null;
		}
	}
	
	private static SList sList() {
		
	// <s list> --> { <statement> }
		
		LinkedList<Statement> sList = new LinkedList<Statement>();

		Statement s = statement();
		sList.add(s);
		while ( state == State.Id || state == State.Keyword_if || state == State.Keyword_switch ||
				state == State.Keyword_while || state == State.Keyword_do || state == State.Keyword_for ||
				state == State.Keyword_print || state == State.LBrace) {
			
			s = statement();
			sList.add(s); // append "statement" to the end of "assignmentList"
		}
		return new SList(sList);
	}

	private static Expr expr() {
	
	// <expr> --> <boolTerm> { "||" <boolTerm> }
			
		LinkedList<BoolTermItem> boolTermItemList = new LinkedList<BoolTermItem>();

		BoolTerm boolTermItem = boolTerm();
		boolTermItemList.add(new SingleBoolTermItem(boolTermItem));
		while ( state == State.Or ) {
			getToken();
			boolTermItem = boolTerm();
			boolTermItemList.add(new OrBoolTermItem(boolTermItem));
		}
		return new Expr(boolTermItemList);
	}

	private static BoolTerm boolTerm() {
		
	// <boolTerm> --> <boolPrimary> { "&&" <boolPrimary> }
					
		LinkedList<BoolPrimaryItem> boolPrimaryItemList = new LinkedList<BoolPrimaryItem>();

		BoolPrimary boolPrimaryItem = boolPrimary();
		boolPrimaryItemList.add(new SingleBoolPrimaryItem(boolPrimaryItem));
		while ( state == State.And ) {
			getToken();
			boolPrimaryItem = boolPrimary();
			boolPrimaryItemList.add(new AndBoolPrimaryItem(boolPrimaryItem));
		}
		return new BoolTerm(boolPrimaryItemList);
	}
	
	public static BoolPrimary boolPrimary() {
		
	// <boolPrimary> --> <E> [ <rel op> <E> ]
		
		E e = E();
		if (state == State.Lt || state == State.Le || state == State.Gt ||
			state == State.Ge || state == State.Eq || state == State.Neq) {

			RelOp relOp = new RelOp(t.toString());
			getToken();
			E e2 = E();
			return new BoolPrimary(e, relOp, e2);		
		}
		return new BoolPrimary(e);	
	}

	public static E E() {

	// <E> --> <term> { (+|-) <term> }
	
		LinkedList<TermItem> termItemList = new LinkedList<TermItem>();

		Term term = term();
		termItemList.add(new SingleTermItem(term));
		while ( state == State.Add | state == State.Sub ) {
			State op = state;
			getToken();
			term = term();
			if ( op == State.Add )
				termItemList.add(new AddTermItem(term));
			else // op == State.Minus
				termItemList.add(new SubTermItem(term));
		}
		return new E(termItemList);
	}

	public static Term term() {

	// <term> --> <primary> { (*|/) <primary> }

		LinkedList<PrimaryItem> primaryItemList = new LinkedList<PrimaryItem>();

		Primary primary = primary();
		primaryItemList.add(new SinglePrimaryItem(primary));
		while ( state == State.Mul | state == State.Div ) {
			State op = state;
			getToken();
			primary = primary();
			if ( op == State.Mul )
				primaryItemList.add(new MulPrimaryItem(primary));
			else // op == State.Div
				primaryItemList.add(new DivPrimaryItem(primary));
		}
		return new Term(primaryItemList);
	}

	public static Primary primary() {

	// <primary> --> <id> | <int> | <float> | <floatE> | <boolLiteral> | "(" <expr> ")" | - <primary> | ! <primary> 

		switch ( state ) {
		
			case Id:
										
				Id id = new Id(t);
				getToken();
				return id;
				
			case Int:

				Int intElem = new Int(Integer.parseInt(t));
				getToken();
				return intElem;

			case Float: case FloatE:

				Floatp floatElem = new Floatp(Float.parseFloat(t));
				getToken();
				return floatElem;

			case LParen:
				
				getToken();
				Expr e = expr();
				if ( state == State.RParen )
				{
					getToken();
					Parenthesized paren = new Parenthesized(e);
					return paren;
				}
				else
				{
					errorMsg(1);
					return null;
				}
				
			case Keyword_true: case Keyword_false:
				
				BoolLiteral bl = new BoolLiteral(t);
				getToken();
				return bl;
				
			case Sub:
				
				getToken();
				Primary p = primary();
				NPrimary nPrimary = new NPrimary(p);
				return nPrimary;
				
			case Inv:
				
				getToken();
				Primary p2 = primary();
				EPrimary ePrimary = new EPrimary(p2);
				return ePrimary;

			default:
				
				errorMsg(2);
				return null;
		}
		
	}

	public static void errorMsg(int i) {
		errorFound = true;
		
		display(t + " : Syntax Error, unexpected symbol where");

		switch( i ) {
			case 1:	displayln(" arith op or ) expected"); return;
			case 2: displayln(" id, int, float, or ( expected"); return;
			case 3:	displayln(" = expected"); return;
			case 4:	displayln(" ; expected"); return;
			case 5:	displayln(" id expected"); return;
			case 6: displayln(" ) expected"); return;
			case 7: displayln(" ( expected"); return;
			case 8: displayln(" { expected"); return;
			case 9: displayln(" } expected"); return;
			case 10: displayln(" while expected"); return;
			case 11: displayln(" Statement Failed"); return;
			case 12: displayln(" Case or Default Expected"); return;
			case 13: displayln(" : Expected"); return;
			case 14: displayln(" Bool Term Expected"); return;
		}
	}

	public static void main(String argv[]) {
		
		// argv[0]: input file containing an assignment list
		// argv[1]: output file displaying the parse tree
		
		setIO( argv[0], argv[1] );

		getToken();

		Statement statement = statement();

		if ( ! t.isEmpty() )
			display(t + " : Syntax Error, unexpected symbol");
		else if ( ! errorFound )
			statement.printParseTree(""); // print the parse tree in linearly indented form in argv[1] file
//			statement.emitInstructions();

		closeIO();
	}
}