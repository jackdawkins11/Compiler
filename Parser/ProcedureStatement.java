
package Parser;
import java.util.Vector;

import Scanner.*;

public class ProcedureStatement extends Node{
	
	private boolean tryIDParenExpressionListParen( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		ID id = new ID( all_tokens, i );

		if( id.accepted() ){

			i += id.numLeaves();

			children.add( id );

			LRParen leftRoundParen = new LRParen( all_tokens, i );

			if( leftRoundParen.accepted() ){

				i += leftRoundParen.numLeaves();

				children.add( leftRoundParen );

				ExpressionList expressionList = new ExpressionList( all_tokens, i );

				if( expressionList.accepted() ){

					i += expressionList.numLeaves();

					children.add( expressionList );

					RRParen rightRoundParen = new RRParen( all_tokens, i );

					if( rightRoundParen.accepted() ){

						children.add( rightRoundParen );

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

		System.out.println( "procedure_statment. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}


	ProcedureStatement( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryIDParenExpressionListParen( all_tokens, i ) ){

			didAccept = true;

		}else if( tryID( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


