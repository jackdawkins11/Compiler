
package symbolTable;

/*
 *
 * Java Utilities used to
 *
 */

import java.util.HashMap;
import java.util.Vector;

/*
 * The symbol table is a stack
 * of hashmaps, each of which represents
 * a scope for a variable. So a variable added
 * stays around until the height of the stack
 * goes below where it was when it was added.
 */

public class SymbolTable{

	Vector< HashMap< String, Symbol > > symbolMap = new Vector< HashMap< String, Symbol > >();

	public int getDepth(){

		return symbolMap.size() - 1 ;

	}

	public void returnToDepth( int depth ){

		symbolMap.setSize( depth + 1 );

	}

	public void pushScope(){

		symbolMap.add( new HashMap< String, Symbol >() );

	}

	public void popScope(){

		symbolMap.remove( symbolMap.size() - 1 );

	}

	public void addSymbol( EnumId idType, EnumVar varType, String lexeme ){

		Symbol newSymbol = new Symbol( idType, varType, lexeme );

		symbolMap.lastElement().put( lexeme, newSymbol );

	}
	
	public void addSymbol( EnumId idType, String lexeme ){

		Symbol newSymbol = new Symbol( idType, EnumVar.NULL, lexeme );

		symbolMap.lastElement().put( lexeme, newSymbol );

	}

	public boolean isProgramName( String lexeme ){

		boolean foundProgram = false;

		for( int i = symbolMap.size() - 1;
				0 <= i && !foundProgram;
				i--){

			if( symbolMap.get( i ).containsKey( lexeme ) ){

				Symbol symbol = symbolMap.get( i ).get( lexeme );

				if( symbol.getIdType() == EnumId.PROGRAM ){

					foundProgram = true;

				}
			
			}

		}

		return foundProgram;

	}
	
	public boolean isFunctionName( String lexeme ){

		boolean foundFunction = false;

		for( int i = symbolMap.size() - 1;
				0 <= i && !foundFunction;
				i--){

			if( symbolMap.get( i ).containsKey( lexeme ) ){

				Symbol symbol = symbolMap.get( i ).get( lexeme );

				if( symbol.getIdType() == EnumId.FUNCTION ){

					foundFunction = true;

				}
			
			}

		}

		return foundFunction;

	}
	
	public boolean isVariableRealName( String lexeme ){

		boolean foundVariable = false;

		for( int i = symbolMap.size() - 1;
				0 <= i && !foundVariable;
				i--){

			if( symbolMap.get( i ).containsKey( lexeme ) ){

				Symbol symbol = symbolMap.get( i ).get( lexeme );

				if( symbol.getIdType() == EnumId.VARIABLE 
				 && symbol.getVarType() == EnumVar.REAL ){

					foundVariable = true;

				}
			
			}

		}

		return foundVariable;
	}
	
	public boolean isVariableIntegerName( String lexeme ){

		boolean foundVariable = false;

		for( int i = symbolMap.size() - 1;
				0 <= i && !foundVariable;
				i--){

			if( symbolMap.get( i ).containsKey( lexeme ) ){

				Symbol symbol = symbolMap.get( i ).get( lexeme );

				if( symbol.getIdType() == EnumId.VARIABLE 
				 && symbol.getVarType() == EnumVar.INTEGER ){

					foundVariable = true;

				}
			
			}

		}

		return foundVariable;
	}
	
	public boolean isArrayRealName( String lexeme ){

		boolean foundArray = false;

		for( int i = symbolMap.size() - 1;
				0 <= i && !foundArray;
				i--){

			if( symbolMap.get( i ).containsKey( lexeme ) ){

				Symbol symbol = symbolMap.get( i ).get( lexeme );

				if( symbol.getIdType() == EnumId.ARRAY 
				 && symbol.getVarType() == EnumVar.REAL ){

					foundArray = true;

				}
			
			}

		}

		return foundArray;
	}
	
	public boolean isArrayIntegerName( String lexeme ){

		boolean foundArray = false;

		for( int i = symbolMap.size() - 1;
				0 <= i && !foundArray;
				i--){

			if( symbolMap.get( i ).containsKey( lexeme ) ){

				Symbol symbol = symbolMap.get( i ).get( lexeme );

				if( symbol.getIdType() == EnumId.ARRAY 
				 && symbol.getVarType() == EnumVar.INTEGER ){

					foundArray = true;

				}
			
			}

		}

		return foundArray;
	}
			
};	

