
package symbolTable;

/*
 *
 * Java Utilities used to
 *
 */

import java.util.HashMap;
import java.util.Vector;
import java.util.Set;

/*
 * The symbol table is a stack
 * of hashmaps, each of which represents
 * a scope for a identifier. So an identifier added
 * stays around until the height of the stack
 * goes below where it was when it was added.
 */

public class SymbolTable{

	Vector< HashMap< String, Symbol > > symbolMap = new Vector< HashMap< String, Symbol > >();

	public void print(){

		Set<String> keySet = symbolMap.lastElement().keySet();

		for( int  i= 0 ; i < keySet.size(); i++){

			EnumId idType = symbolMap.lastElement().get( keySet.toArray()[ i ] ).getIdType();

			EnumVar varType = symbolMap.lastElement().get( keySet.toArray()[ i ] ).getVarType();

			String lexeme = symbolMap.lastElement().get( keySet.toArray()[ i ] ).getIdentifier();

			System.out.println( idType.toString() + "  " + varType.toString() + "  " + lexeme );

		}

	}

	public SymbolTable(){
		pushScope();
	}

	/*
	 * get depth
	 */

	public int getDepth(){

		return symbolMap.size() - 1 ;

	}

	/*
	 * pop a certain amount of times
	 */

	public void returnToDepth( int depth ){

		symbolMap.setSize( depth + 1 );

	}

	/*
	 * push stack
	 */

	public void pushScope(){

		symbolMap.add( new HashMap< String, Symbol >() );

	}

	/*
	 * pop stack
	 */

	public void popScope(){

		symbolMap.remove( symbolMap.size() - 1 );

	}

	/*
	 * add symbol
	 */

	public void add( Symbol newSymbol ){

		symbolMap.lastElement().put( newSymbol.getIdentifier(), newSymbol );

	}

	/*
	 * delete symbol of the given type
	 * with the given lexeme
	 */
	
	public void delete( EnumId idType, String identifier ){

		boolean deletedFirstInstance = false;

		for( int i = symbolMap.size() - 1;
				0 <= i && !deletedFirstInstance;
			       	i--){

			if( symbolMap.get( i ).containsKey( identifier ) ){

				Symbol symbol = symbolMap.get( i ).get( identifier );

				if( symbol.getIdType() == idType ){

					symbolMap.get( i ).remove( identifier, symbol );

					deletedFirstInstance = true;

				}
			
			}

		}

	}

	/*
	 * search for a symbol
	 * witht the given type and lexeme
	 */
	
	public boolean exists( EnumId idType, String identifier ){

		boolean foundSymbol = false;

		for( int i = symbolMap.size() - 1;
				0 <= i && !foundSymbol;
				i--){

			if( symbolMap.get( i ).containsKey( identifier ) ){

				Symbol symbol = symbolMap.get( i ).get( identifier );

				if( symbol.getIdType() == idType ){

					foundSymbol = true;

				}
			
			}

		}

		return foundSymbol;

	}

};	

