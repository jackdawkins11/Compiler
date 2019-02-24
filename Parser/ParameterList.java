
package Parser;
import java.util.Vector;

import Scanner.*;

public class ParameterList extends Node{
	
	private boolean tryIdentifierListColonTypeSemicolonParameterList( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		IdentifierList identifierList = new IdentifierList( all_tokens, i );

		if( identifierList.accepted() ){

			children.add( identifierList );

			i += identifierList.numLeaves();

			Colon colon = new Colon( all_tokens, i );

			if( colon.accepted() ){

				i += colon.numLeaves();

				children.add( colon );

				Type type = new Type( all_tokens, i );

				if( type.accepted() ){

					children.add( type );

					i += type.numLeaves();

					Semicolon semicolon = new Semicolon( all_tokens, i );

					if( semicolon.accepted() ){

						children.add( semicolon );

						i += semicolon.numLeaves();

						ParameterList parameterList = new ParameterList( all_tokens, i );

						if( parameterList.accepted() ){

							children.add( parameterList );

							success = true;

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



	private boolean tryIdentifierListColonType( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		IdentifierList identifierList = new IdentifierList( all_tokens, i );

		if( identifierList.accepted() ){

			children.add( identifierList );

			i += identifierList.numLeaves();

			Colon colon = new Colon( all_tokens, i );

			if( colon.accepted() ){

				i += colon.numLeaves();

				children.add( colon );

				Type type = new Type( all_tokens, i );

				if( type.accepted() ){

					children.add( type );

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

		System.out.println( "parameter list. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	ParameterList( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryIdentifierListColonTypeSemicolonParameterList( all_tokens, i ) ){

			didAccept = true;

		}else if( tryIdentifierListColonType( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


