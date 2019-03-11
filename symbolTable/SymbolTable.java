
package symbolTable;

import java.util.HashMap;
import java.util.Vector;
import java.util.Set;

public class SymbolTable{

	Vector< HashMap< String, Symbol > > symbolMap = new Vector< HashMap< String, Symbol > >();

	public void print(){

		for( int depth = symbolMap.size() - 1;
				0 <= depth; depth-- ){

			System.out.println("symbols at level " + depth );

			Set< String > keySet = symbolMap.get( depth ).keySet();

			for( int i= 0 ; i < keySet.size(); i++){

				symbolMap.get( depth ).get( keySet.toArray()[ i ] ).print();
			
			}

		}

	}

	public SymbolTable(){
		
		pushScope();
	
	}
	
	public void pushScope(){

		symbolMap.add( new HashMap< String, Symbol >() );

	}


	public void popScope(){

		symbolMap.remove( symbolMap.size() - 1 );

	}

	public void add( Symbol newSymbol ){

		symbolMap.lastElement().put( newSymbol.getIdentifier(), newSymbol );

	}

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

	public void delete( EnumId idType, String identifier ){

		boolean foundSymbol = false;

		for( int i = symbolMap.size() - 1;
				0 <= i && !foundSymbol;
				i--){

			if( symbolMap.get( i ).containsKey( identifier ) ){

				Symbol symbol = symbolMap.get( i ).get( identifier );

				if( symbol.getIdType() == idType ){

					foundSymbol = true;

					symbolMap.get( i ).remove( symbol.getIdentifier(), symbol );

				}
			
			}

		}

	}

};	

