
package Parser;
import java.util.Vector;

import Scanner.*;

public class ExpressionList extends Node{

	private boolean tryExpressionCommaExpressionList( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Expression expression = new Expression( all_tokens, i );

		if( expression.accepted() ){

			children.add( expression );

			i += expression.numLeaves();

			Comma comma = new Comma( all_tokens, i );

			if( comma.accepted() ){

				children.add( comma );

				i += comma.numLeaves();

				ExpressionList expressionList = new ExpressionList( all_tokens, i );

				if( expressionList.accepted() ){

					children.add( expressionList );

					success = true;

				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	private boolean tryExpression( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Expression expression = new Expression( all_tokens, i );

		if( expression.accepted() ){

			children.add( expression );

			success = true;

		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	@Override
	public void print(){

		System.out.println( "Expression List. Children: " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}


	ExpressionList( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryExpressionCommaExpressionList( all_tokens, i ) ){

			didAccept = true;

		}else if( tryExpression( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


