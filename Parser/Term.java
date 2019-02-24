
package Parser;
import java.util.Vector;

import Scanner.*;

public class Term extends Node{

	private boolean tryFactorTermPart( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Factor factor = new Factor( all_tokens, i );

		if( factor.accepted() ){

			i += factor.numLeaves();

			children.add( factor );

			TermPart termPart = new TermPart( all_tokens, i );

			if( termPart.accepted() ){

				children.add( termPart );

				success = true;

			}

		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	@Override
	public void print(){

		System.out.println( "Term. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}


	Term( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryFactorTermPart( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


