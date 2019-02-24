
package Parser;
import java.util.Vector;

import Scanner.*;

public class Variable extends Node{

	private boolean tryIDParenExpressionParen( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		ID id = new ID( all_tokens, i );

		if( id.accepted() ){

			i += id.numLeaves();

			children.add( id );

			LSParen leftSquareParen = new LSParen( all_tokens, i );

			if( leftSquareParen.accepted() ){

				i += leftSquareParen.numLeaves();

				children.add( leftSquareParen );

				Expression expression = new Expression( all_tokens, i );

				if( expression.accepted() ){

					i += expression.numLeaves();

					children.add( expression );

					RSParen rightSquareParen = new RSParen( all_tokens, i );

					if( rightSquareParen.accepted() ){

						children.add( rightSquareParen );

						success = true;

					}

				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	private boolean tryID( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		ID id = new ID( all_tokens, i );

		if( id.accepted() ){

			children.add( id );

			success = true;
		
		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	@Override
	public void print(){

		System.out.println( "variable. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	Variable( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryIDParenExpressionParen( all_tokens, i ) ){

			didAccept = true;

		}else if( tryID( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


