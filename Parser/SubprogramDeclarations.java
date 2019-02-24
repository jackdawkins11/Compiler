
package Parser;
import java.util.Vector;

import Scanner.*;

public class SubprogramDeclarations extends Node{
	
	private boolean trySubprogramDeclarationSemicolonSubprogramDeclarations( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		SubprogramDeclaration subprogramDeclaration = new SubprogramDeclaration( all_tokens, i );

		if( subprogramDeclaration.accepted() ){

			children.add( subprogramDeclaration );

			i += subprogramDeclaration.numLeaves();

			Semicolon semicolon = new Semicolon( all_tokens, i );

			if( semicolon.accepted() ){

				children.add( semicolon );

				i += semicolon.numLeaves();

				SubprogramDeclarations subprogramDeclarations = new SubprogramDeclarations( all_tokens, i );

				if( subprogramDeclarations.accepted() ){

					children.add( subprogramDeclarations );
				
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

		System.out.println( "subprogram declarations. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	SubprogramDeclarations( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( trySubprogramDeclarationSemicolonSubprogramDeclarations( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = true;

		}

	}

}


