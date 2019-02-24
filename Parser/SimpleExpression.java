
package Parser;
import java.util.Vector;

import Scanner.*;

public class SimpleExpression extends Node{

	private boolean tryTermSimplePart( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Term term = new Term( all_tokens, i );

		if( term.accepted() ){

			children.add( term );

			i += term.numLeaves();

			SimplePart simplePart = new SimplePart( all_tokens, i );

			if( simplePart.accepted() ){

				children.add( simplePart );

				success = true;

			}

		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	private boolean trySignTermSimplePart( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Sign sign = new Sign( all_tokens, i );

		if( sign.accepted() ){

			children.add( sign );

			i += sign.numLeaves();

			Term term = new Term( all_tokens, i );
	
			if( term.accepted() ){

				children.add( term );

				i += term.numLeaves();

				SimplePart simplePart = new SimplePart( all_tokens, i);

				if( simplePart.accepted() ){

					children.add( simplePart );

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

		System.out.println( "Simple Expression. Children: " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}


	SimpleExpression( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryTermSimplePart( all_tokens, i ) ){

			didAccept = true;

		}else if( trySignTermSimplePart( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


