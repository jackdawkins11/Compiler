
package Parser;
import java.util.Vector;

import Scanner.*;

public class OptionalStatements extends Node{
	
	private boolean tryStatementList( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		StatementList statementList = new StatementList( all_tokens, i );

		if( statementList.accepted() ){

			children.add( statementList );

			success = true;

		}

		if( !success ){

			children.clear();

		}

		return success;
	
	}
	
	@Override
	public void print(){

		System.out.println( "optional statments. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	OptionalStatements( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryStatementList( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = true;

		}

	}

}


