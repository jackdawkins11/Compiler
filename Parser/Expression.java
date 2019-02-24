
package Parser;
import java.util.Vector;

import Scanner.*;

public class Expression extends Node{

	private boolean trySimpleExpressionRelopSimpleExpression( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		SimpleExpression simpleExpression = new SimpleExpression( all_tokens, i );

		if( simpleExpression.accepted() ){

			children.add( simpleExpression );

			i += simpleExpression.numLeaves();

			Relop relop = new Relop( all_tokens, i );

			if( relop.accepted() ){

				children.add( relop );

				i += relop.numLeaves();

				SimpleExpression simpleExpression2 = new SimpleExpression( all_tokens, i  );

				if( simpleExpression2.accepted() ){

					children.add( simpleExpression2 );

					success = true;

				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	private boolean trySimpleExpression( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		SimpleExpression simpleExpression = new SimpleExpression( all_tokens, i );

		if( simpleExpression.accepted() ){

			children.add( simpleExpression );

			success = true;

		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	@Override
	public void print(){

		System.out.println( "Expression. Children: " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}


	Expression( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( trySimpleExpressionRelopSimpleExpression( all_tokens, i ) ){

			didAccept = true;

		}else if( trySimpleExpression( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


