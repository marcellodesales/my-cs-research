package br.ufal.tci.interpreter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Hashtable;
import java.util.Enumeration;

/**
 * This is a postfix calculator. When run, it should prompt
 * the user for a mathmatical expression in infix notation.  The
 * calculator will convert the expression to postfix notation and
 * output both the postfix form and the result of that expression.
 * This calculator only the following characters as operators:
 * "+-\*"
 *
 * Update - April 26, 2002
 * The new feature of left-value and the assembler notation was added.
 * Besides converting the exepression, it can generate and return the assembler
 * (in a pseudo-way) based on the postfix result and return the value of a
 * left-value, if it exists in the expression.
 * Now it supports the same characters operators, plus "=" and a variable, i.e. "X".
 *
 * @author Martin Smith - Template structure of stack<BR>
 *         Martin Smith - Completed convert, calculate and main<BR>
 *         Marcello de Sales - Simulation of the assembler<BR>
 *         Marcello de Sales - Application of the Lvalue notation
 * @see Stack
 * @see StackException
 */
public class Interpreter{
	
	/** The description of the push stack operation = "PUSH" */
	public static final String PUSH_OPERATION   = "<b>PUSH</b>";
	/** The description of the pop stack operation = "POP" */
	public static final String POP_OPERATION    = "<b>POP</b>";
	/** The description of the left-value assembler operation = "LVALUE" */
	public static final String LVALUE_OPERATION = "<b>LVALUE</b>";
	/** The description of the right-value assembler operation = "RVALUE" */
	public static final String RVALUE_OPERATION = "<b>RVALUE</b>";
	/** The description of the MOV assembler operation = "MOV" */
	public static final String MOV_OPERATION    = "<b>MOV</b>";
	/** The description of the adition assembler operation = "ADD" */
	public static final String ADD_FUNCTION     = "<b>ADD</b>";
	/** The description of the multiplication assembler operation = "MULT" */
	public static final String MULT_FUNCTION    = "<b>MULT</b>";
	/** The description of the subtraction assembler operation = "SUB" */
	public static final String SUB_FUNCTION     = "<b>SUB</b>";
	/** The description of the division assembler operation = "DIV" */
	public static final String DIV_FUNCTION     = "<b>DIV</b>";
	/** The Infix operators = "( ) * - / + =" */
	public static final String INFIX_OPERATORS   = "()*/-+= ";
	/** The Postfix operators = "* / - + = "*/
	public static final String POSTFIX_OPERATORS = "*/-+= ";
	
	/** The assembler representation of the postfix form. Each position is a String. */
	private Vector assembler;
	/** The Left-Value of the expression. */
	private String lvalue;
	/** The infix expression of given to the constructor. */
	private String infixExpression;
	/** The postfix expression as the result of the interpreter. */
	private String postfixExpression;
	/** The result of the interpreter. */
	private double result;
	/** The inicial table of symbols  */
	private Hashtable tableOfSymbolsIni;
	/** The final table of symbols  */
	private Hashtable tableOfSymbolsEnd;
	/** The the left-value needed to complete the evalution*/
	private String[] rvaluesNeeded = {};
	
    /**
     * Default Constructor.
     * We may be tempted to have a Stack instance variable for
     * this class, but we'll let each method have it's own personal
     * Stack.  There's nothing in the Stack that each method needs to
	 * share.
	 * If the right-value variables exists (this.getNumberOfRValues = 0), then
	 * need first set them, before getting the result.
	 */
    public Interpreter(String infixExpression) throws IrregularExpressionException{
		this.infixExpression = infixExpression;
		this.assembler = new Vector();
		this.tableOfSymbolsIni = this.getInicialTableOfSymbols(infixExpression);
		if (this.tableOfSymbolsIni.size() > 1){
			this.setRValues(this.tableOfSymbolsIni);
		}
		this.tableOfSymbolsEnd = new Hashtable();
		this.postfixExpression = this.convert(infixExpression);
		//this.result = this.calculate(this.postfixExpression);
    }
	
	/** Sets the array containing the right-value variables. */
	private void setRValues(Hashtable tableOfSymbolsIni){
		this.rvaluesNeeded = new String[tableOfSymbolsIni.size()-1];
		Enumeration e = tableOfSymbolsIni.keys();
		int i = 0;
		while(e.hasMoreElements()){
			String var = (String)e.nextElement();
			if (!var.equals(this.lvalue)){
				this.rvaluesNeeded[i] = var;
				i++;
			}
		}
	}
	
	/**
	 * Returns the vector of String, representing each line of the assembler
	 * representation of a given postfix form to the Calculate method.
	 * It can be with or without left-variables.
	 * @return The assembler representation of a postfix form
	 */
	public Vector getAssembler(){
		return this.assembler;
	}
	
	/** @return If the expression has a Left-value.  */
	public boolean expressionHasLValue(){
		return (this.lvalue != null);
	}
	
	/** @return The left-value variable. */
	public String getLValue(){
		return this.expressionHasLValue() ? this.lvalue : "";
	}
	
	/** Sets the value of the left-value of the expression.  */
	private void setLValue(String lvalue){
		this.lvalue = lvalue;
	}
	
	/** Gets the value of postfix expression. */
	public String getPostfixNotation(){
		return this.postfixExpression;
	}
	
	/** Gets the value of infix expression. */
	public String getInfixNotation(){
		return this.infixExpression;
	}
	
	/** Gets the value of result produced by the postfix expression evolution.  */
	public double getResult(){
		return this.result;
	}
	
	/** Gets the initial table of symbos. */
	public Hashtable getInicialTableOfSymbols(){
		return this.tableOfSymbolsIni;
	}
	
	/** Gets the final table of symbos. */
	public Hashtable getEndTableOfSymbols(){
		return this.tableOfSymbolsEnd;
	}
	
	/** Gets the array of the RValues. */
	public String[] getRValuesNeeded(){
		return this.rvaluesNeeded;
	}
	
	/** Gets number of RValues variables. */
	public int getNumberOfRValues(){
		return this.rvaluesNeeded.length;
	}
	
	/**
	 * Sets the value of a RValue variable.
	 * @param rValueVar The nume of the right-value variable.
	 * @param value The value of the right-value variable.
	 * @return True if the variable was found or false case not.
	 */
	public boolean setRValue(String rValueVar, String value){
		boolean isRValue = false;
		if (this.tableOfSymbolsIni.containsKey(rValueVar)){
			isRValue = true;
			this.tableOfSymbolsIni.remove(rValueVar);
			this.tableOfSymbolsIni.put(rValueVar,value);
		}
		return isRValue;
	}
		
	/**
	 * Gets the inicial table of symbols.
	 * @param The infix value or the inicial expression passed to the constructor.
	 * @return The table containing all the right-values variables and its values plus
	 * the left-value value.
	 **/
	private Hashtable getInicialTableOfSymbols(String infix){
		StringTokenizer tok = new StringTokenizer(infix,Interpreter.INFIX_OPERATORS,true);
		Hashtable initialTable = new Hashtable();
		while(tok.hasMoreTokens()){
			String token = tok.nextToken();
			if (token.equals(" ")) continue;
			try {
				int tokenValue = Integer.parseInt(token);
			} catch (NumberFormatException nfe){
				//Checks if the token is not an operator
				if ((this.priority(token) == -1) && (!token.equals("=")) && (!token.equals(")") && (!token.equals("(")))){
					// Or assume it's variable = Lvalue or Rvalue
					if (!this.expressionHasLValue())
						this.setLValue(token);
					initialTable.put(token,"Vazio");
				}
			}
		}
		return initialTable;
	}
	
    /**
     * Converts the given String from infix to postfix
     * notation.  We will assume that the input String is
     * well formed.
     *
     * @param infix An String representation of an expression in
     *              infix notation.
     * @return The expression given in postfix notation.
     */
    private String convert(String infix) throws IrregularExpressionException{
		StringTokenizer test = new StringTokenizer(infix,Interpreter.INFIX_OPERATORS, true);
		while (test.hasMoreTokens()){
			String token = (String)test.nextToken();
			if (this.isReservedWord(token))
				throw new IrregularExpressionException("A variável "+token+" é uma palavra reservada!");
		}
		
		StringTokenizer tok = new StringTokenizer(infix,Interpreter.INFIX_OPERATORS, true);
        Stack s = new Stack(1);
        String result = "";
				
        while(tok.hasMoreTokens()) {
            String token = (String)tok.nextToken();

            // comparisons
            if (token.equals(" ")) {
                // if token is space, discard
			} else if (token.equals("=")) {
				// if token is equals value, then push on stack
				s.push(token);
            } else if (token.equals("(")) {
                // open paren
                s.push(token);
            } else if (token.equals(")")) {
                // close paren
                try {
                    while (!s.peek().equals("(")) {
                        result += s.pop() + " ";
                    }
                    s.pop(); // discard "("
                } catch (StackException se) {
                    // shouldn't happen on valid input
                    System.out.println("Stack Exception in close paren case");
                    System.exit(-1);
                }
            } else if (token.equals("*") || token.equals("/")
                    || token.equals("-") || token.equals("+")) {
                // any operator
                try {
                    while (!s.isEmpty() && priority(token) <= priority((String)s.peek())) {
                        result += s.pop() + " ";
                    }
                    s.push(token);
                } catch (StackException se) {
                    // shouldn't happen on valid input
                    System.out.println("Stack Exception in operator case");
                    System.exit(-1);
                }
            } else {
                // a number
                result += token + " ";
            }
        }

        // no more tokens
        try {
            while(!s.isEmpty()) {
                result += s.pop() + " ";
            }
        } catch (StackException se) {
            // shouldn't happen on valid input
            System.out.println("Stack Exception in flush stack case");
            System.exit(-1);
        }
        return result;
    }
	
    /**
	 * Verifies if the token is a reserved word of the interpreter, like either
	 * the operation or function words.
     */
    private boolean isReservedWord(String token) {
		return (token.equals(Interpreter.ADD_FUNCTION) ||
				token.equals(Interpreter.DIV_FUNCTION) ||
				token.equals(Interpreter.LVALUE_OPERATION) ||
				token.equals(Interpreter.MOV_OPERATION) ||
				token.equals(Interpreter.MULT_FUNCTION) ||
				token.equals(Interpreter.POP_OPERATION) ||
				token.equals(Interpreter.PUSH_OPERATION) ||
				token.equals(Interpreter.RVALUE_OPERATION) ||
				token.equals(Interpreter.SUB_FUNCTION)) ? true : false;
    }

    /**
     * Convert tokens into integer priorities which can easily be
     * compared.
     */
    private int priority(String token) {
        if (token == null) {
            System.exit(-2);
        } else if (token.equals("(")) {
            return 0;
        } else if (token.equals("+")) {
            return 1;
        } else if (token.equals("-")) {
            return 2;
        } else if (token.equals("*")) {
            return 3;
        } else if (token.equals("/")) {
            return 4;
        }

        // Unknown case
        return -1;
    }
	
    /**
     * Evaluates an expression in postfix notation and returns
     * the result.
     *
     * @param postfix An String representation of an expression
     *                in infix notation.
     * @return The double value of the calculated expression.
     */
    public double calculate(String postfix){
		
		this.tableOfSymbolsEnd = (Hashtable)this.tableOfSymbolsIni.clone();
		
        StringTokenizer tok = new StringTokenizer(postfix,Interpreter.POSTFIX_OPERATORS, true);
        Stack s = new Stack(1);
		while (tok.hasMoreTokens()) {
            String token = tok.nextToken();
            try {
                // IMPORTANT
                // First item popped is the second operand of operator.
                // This is important or minus and divide will be broken.
                if (token.equals(" ")) {
					continue;
					// discard space
				} else if (token.equals("*")) {
                    double x = ((Double)s.pop()).doubleValue();
					double y = ((Double)s.pop()).doubleValue();
					String pop1 = Interpreter.POP_OPERATION+" "+x;
					String pop2 = Interpreter.POP_OPERATION+" "+y;
					String mult = Interpreter.MULT_FUNCTION+" "+x+" , "+y;
					Double res = new Double(x * y);
					String result = Interpreter.PUSH_OPERATION+" "+res;
					this.assembler.add(pop1);
					this.assembler.add(pop2);
					this.assembler.add(mult);
					this.assembler.add(result);
                    s.push(res);

                } else if (token.equals("/")) {
                    double x = ((Double)s.pop()).doubleValue();
                    double y = ((Double)s.pop()).doubleValue();
					String pop1 = Interpreter.POP_OPERATION+" "+x;
					String pop2 = Interpreter.POP_OPERATION+" "+y;
					String div  = Interpreter.DIV_FUNCTION+" "+y+" , "+x;
					Double res = new Double(y / x);
					String result = Interpreter.PUSH_OPERATION+" "+res;
					this.assembler.add(pop1);
					this.assembler.add(pop2);
					this.assembler.add(div);
					this.assembler.add(result);
					s.push(res);

                } else if (token.equals("-")) {
                    double x = ((Double)s.pop()).doubleValue();
                    double y = ((Double)s.pop()).doubleValue();
					String pop1 = Interpreter.POP_OPERATION+" "+x;
					String pop2 = Interpreter.POP_OPERATION+" "+y;
					String sub  = Interpreter.SUB_FUNCTION+" "+y+" , "+x;
					Double res = new Double(y - x);
					String result = Interpreter.PUSH_OPERATION+" "+res;
					this.assembler.add(pop1);
					this.assembler.add(pop2);
					this.assembler.add(sub);
					this.assembler.add(result);
					s.push(res);

                } else if (token.equals("+")) {
                    double x = ((Double)s.pop()).doubleValue();
                    double y = ((Double)s.pop()).doubleValue();
					String pop1 = Interpreter.POP_OPERATION+" "+x;
					String pop2 = Interpreter.POP_OPERATION+" "+y;
					String sum  = Interpreter.ADD_FUNCTION+" "+x+" , "+y;
					Double res = new Double(x + y);
					String result = Interpreter.PUSH_OPERATION+" "+res;
					this.assembler.add(pop1);
					this.assembler.add(pop2);
					this.assembler.add(sum);
					this.assembler.add(result);
					s.push(res);
					
				} else if (token.equals("=")){
					double var = ((Double)s.peek()).doubleValue();
					String mov = Interpreter.MOV_OPERATION+" "+var+" , "+this.getLValue();
					this.assembler.add(mov);
					
				} else {
					try {
						int tokenValue = Integer.parseInt(token);
                    	// assume it's a number
						// constructor for Double will parse the String
						String push = Interpreter.PUSH_OPERATION+" "+token;
						this.assembler.add(push);
						s.push(new Double(token));
					} catch (NumberFormatException nfe){
						
                    	// Or assume it's variable = Lvalue or Rvalue
						if (this.lvalue.equals(token)){
							String lv = Interpreter.LVALUE_OPERATION+" "+token;
							this.assembler.add(lv);
							
						} else { //updates the value of the right-value variable
							String rv = Interpreter.RVALUE_OPERATION+" "+token;
							this.assembler.add(rv);
							double valueOfRValuVar = Double.parseDouble((String)this.tableOfSymbolsIni.get(token));
							s.push(new Double(valueOfRValuVar));
						}
					}
                }
				
			} catch (StackException se) {
                // shouldn't happen on valid input
                System.out.println("Stack Exception in calculate");
                System.exit(-1);
            }
        }
		
        double result = -1.0;

        try {
			result = ((Double)s.pop()).doubleValue();
			if (this.tableOfSymbolsEnd.size() > 0){
				this.tableOfSymbolsEnd.remove(this.lvalue);
				this.tableOfSymbolsEnd.put(this.lvalue,String.valueOf(result));
			}
        } catch (StackException se) {
            // shouldn't happen on valid input
            System.out.println("Stack Exception in calculate");
            System.exit(-1);
		}
	    return result; // Modify this code
    }
	
    /**
     * Program execution begins here.  This method should prompt
     * the user for an expression in infix notation.  This should
     * be done with console Input/Output, no graphical components.
     * Then convert the expression to postfix notation and output
     * the postfix form of the expression.  Then it should calculate
     * the value of the postfix expression and ouput the value and
     * exit.
     *
     * We'll assume that all input from the user is valid.
     *
     * @param array of commandline arguments - not used.
     */
    public static void main(String [] args){
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in),1);

        System.out.print("Digite a expressão infixa: ");
        System.out.flush();

        String infix = null;
        try {
            infix = in.readLine();
        } catch (IOException ioe) {
            System.err.println(ioe);
            System.exit(-1);
        }
        System.out.println();
		
		try{
			Interpreter interpreter = new Interpreter(infix);
        	// convert
        	System.out.println("Expressão Pósfixa: "+interpreter.getPostfixNotation());
			System.out.println();
			
			if (interpreter.getNumberOfRValues() > 0){
				String[] rvalueVar = interpreter.getRValuesNeeded();
				for (int i=0; i < rvalueVar.length; i++){
					System.out.print("Digite o valor da variável RValue "+rvalueVar[i]+": ");
					System.out.flush();
					String rval = null; //the value of the right-value variable
        			try {
            			rval = in.readLine();
        			} catch (IOException ioe) {
            			System.err.println(ioe);
            			System.exit(-1);
        			}
					System.out.println();
					interpreter.setRValue(rvalueVar[i],rval);
				}
			}
				
	        // calculate
			double value = interpreter.calculate(interpreter.getPostfixNotation());
		
			System.out.println("%%%% Montador - Assembler %%%%)");
			Vector assembler = interpreter.getAssembler();
			for (int i = 0; i < assembler.size(); i++){
				String assemblerPart = (String)assembler.get(i);
				System.out.println(assemblerPart);
			}
			
			Hashtable tableIni = interpreter.getInicialTableOfSymbols();
			Hashtable tableEnd = interpreter.getEndTableOfSymbols();
			
			System.out.println("%%%% Tabela de memória inicial de Valors %%%%)");
			Enumeration colNamesTI = tableIni.keys();
			while(colNamesTI.hasMoreElements()){
				String variableName = (String)colNamesTI.nextElement();
				String variableValue = (String)tableIni.get(variableName);
				System.out.println("["+variableName+"] = ["+variableValue+"]");
			}
		
			System.out.println("%%%% Tabela de memória final de Valors %%%%)");
			Enumeration colNamesFI = tableEnd.keys();
			while(colNamesFI.hasMoreElements()){
				String variableName = (String)colNamesFI.nextElement();
				String variableValue = (String)tableEnd.get(variableName);
				System.out.println("["+variableName+"] = ["+variableValue+"]");
			}
			
			String result = "";
			if (!interpreter.getLValue().equals("")){
				result = interpreter.getLValue() + " = " + value;
			} else result = String.valueOf(value);

			System.out.println("Valor da expressão é "+ result);
		} catch (IrregularExpressionException iee){
			iee.printStackTrace();
		}
    }
}
