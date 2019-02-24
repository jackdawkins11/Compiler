
package Parser;
import java.util.Vector;

import Scanner.*;

public class Declarations extends Node{
	
	private boolean tryVarIdentifierListColonTypeSemicolonDeclarations( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Var var = new Var( all_tokens, i );

		if( var.accepted() ){

			children.add( var );

			i += var.numLeaves();

			IdentifierList identifierList = new IdentifierList( all_tokens, i );

			if( identifierList.accepted() ){

				children.add( identifierList );

				i += identifierList.numLeaves();

				Colon colon = new Colon( all_tokens, i );

				if( colon.accepted() ){

					children.add( colon );

					i += colon.numLeaves();

					Type type = new Type( all_tokens, i );

					if( type.accepted() ){

						children.add( type );

						i += type.numLeaves();

						Semicolon semicolon = new Semicolon( all_tokens, i );

						if( semicolon.accepted() ){

							children.add( semicolon );

							i += semicolon.numLeaves();

							Declarations declarations = new Declarations( all_tokens, i );

							if( declarations.accepted() ){

								children.add( declarations );

								success = true;

							}

						}

					}
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

		System.out.println( "delcarations: Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	Declarations( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryVarIdentifierListColonTypeSemicolonDeclarations( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = true;

		}

	}

}


