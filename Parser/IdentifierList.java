
package Parser;
import java.util.Vector;

import Scanner.*;

public class IdentifierList extends Node{
	
	private boolean tryIdCommaIdentifierList( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		ID id = new ID( all_tokens, i );

		if( id.accepted() ){

			children.add( id );

			i += id.numLeaves();

			Comma comma = new Comma( all_tokens, i );

			if( comma.accepted() ){

				children.add( comma );

				i += comma.numLeaves();

				IdentifierList IDList = new IdentifierList( all_tokens, i );

				if( IDList.accepted() ){

					children.add( IDList );

					success  = true;

				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;
	
	}
	
	private boolean tryId( final Vector< Token > all_tokens, int i ){

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

		System.out.println( "identifier list. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	IdentifierList( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryIdCommaIdentifierList( all_tokens, i ) ){

			didAccept = true;

		}else if( tryId( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


