
package Parser;
import java.util.Vector;

import Scanner.*;

public class StatementList extends Node{
	
	private boolean tryStatementSemicolonStatementList( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Statement statement = new Statement( all_tokens, i );

		if( statement.accepted() ){

			i += statement.numLeaves();

			children.add( statement );

			Semicolon semicolon = new Semicolon( all_tokens, i );

			if( semicolon.accepted() ){

				i += semicolon.numLeaves();

				children.add( semicolon );

				StatementList statementList = new StatementList( all_tokens, i );

				if( statementList.accepted() ){

					children.add( statementList );

					success = true;
				
				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;
	
	}
	
	private boolean tryStatement( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Statement statement = new Statement( all_tokens, i );

		if( statement.accepted() ){

			children.add( statement );
					
			success = true;

		}

		if( !success ){

			children.clear();

		}

		return success;
	
	}
	
	@Override
	public void print(){

		System.out.println( "statment list. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	StatementList( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryStatementSemicolonStatementList( all_tokens, i ) ){

			didAccept = true;

		}else if( tryStatement( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


