
package Parser;
import java.util.Vector;

import Scanner.*;

public class TermPart extends Node{

	private boolean tryMulopFactorTermPart( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Mulop mulop = new Mulop( all_tokens, i );

		if( mulop.accepted() ){

			children.add( mulop );

			i += mulop.numLeaves();

			Factor factor = new Factor( all_tokens, i );

			if( factor.accepted() ){

				children.add( factor );

				i += factor.numLeaves();

				TermPart termPart = new TermPart( all_tokens, i );

				if( termPart.accepted() ){

					children.add( termPart );

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

		System.out.println( "term part. Children: " + children.size());

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}


	TermPart( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryMulopFactorTermPart( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = true;

		}

	}

}


