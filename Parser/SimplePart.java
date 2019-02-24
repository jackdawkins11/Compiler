
package Parser;
import java.util.Vector;

import Scanner.*;

public class SimplePart extends Node{

	private boolean tryAddopTermSimplePart( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Addop addop = new Addop( all_tokens, i );

		if( addop.accepted() ){

			children.add( addop );

			i += addop.numLeaves();

			Term term = new Term( all_tokens, i );

			if( term.accepted() ){

				children.add( term );

				i += term.numLeaves();

				SimplePart simplePart = new SimplePart( all_tokens, i  );

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

		System.out.println( "simple part. Children:  " + children.size());

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}


	SimplePart( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryAddopTermSimplePart( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = true;

		}

	}

}


