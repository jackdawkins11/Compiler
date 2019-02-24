
package Parser;
import java.util.Vector;

import Scanner.*;

public class SubprogramDeclaration extends Node{
	
	private boolean trySubprogramHeadDeclarationsCompoundStatement( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		SubprogramHead subprogramHead = new SubprogramHead( all_tokens, i );

		if( subprogramHead.accepted() ){

			children.add( subprogramHead );

			i += subprogramHead.numLeaves();

			Declarations declarations = new Declarations( all_tokens, i );

			if( declarations.accepted() ){

				i += declarations.numLeaves();

				children.add( declarations );

				CompoundStatement compoundStatement = new CompoundStatement( all_tokens, i );

				if( compoundStatement.accepted() ){

					children.add( compoundStatement );

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

		System.out.println( "subprogram declaration. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	SubprogramDeclaration( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( trySubprogramHeadDeclarationsCompoundStatement( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


