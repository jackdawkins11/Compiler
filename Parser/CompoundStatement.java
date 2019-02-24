
package Parser;
import java.util.Vector;

import Scanner.*;

public class CompoundStatement extends Node{
	
	private boolean tryBeginOptionalStatementsEnd( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Begin begin = new Begin( all_tokens, i );

		if( begin.accepted() ){

			i += begin.numLeaves();

			children.add( begin );

			OptionalStatements optionalStatements = new OptionalStatements( all_tokens, i );

			if( optionalStatements.accepted() ){

				i += optionalStatements.numLeaves();

				children.add( optionalStatements );

				End end = new End( all_tokens, i );

				if( end.accepted() ){

					children.add( end );

					success = true;
				
				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;
	
	}
	
	@Override
	public void print(){

		System.out.println( "compoundstatment. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}


	CompoundStatement( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryBeginOptionalStatementsEnd( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


