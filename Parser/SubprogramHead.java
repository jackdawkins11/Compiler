
package Parser;
import java.util.Vector;

import Scanner.*;

public class SubprogramHead extends Node{
	
	private boolean tryFunctionIdArgumentsColonStandardTypeSemicolon( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Function function = new Function( all_tokens, i );

		if( function.accepted() ){

			children.add( function );

			i += function.numLeaves();

			ID id = new ID( all_tokens, i );

			if( id.accepted() ){

				children.add( id );

				i += id.numLeaves();

				Arguments arguments = new Arguments( all_tokens, i );

				if( arguments.accepted() ){

					children.add( arguments );

					i += arguments.numLeaves();

					Colon colon = new Colon( all_tokens, i );

					if( colon.accepted() ){

						children.add( colon );

						i += colon.numLeaves();

						StandardType standardType = new StandardType( all_tokens, i );

						if( standardType.accepted() ){

							children.add( standardType );

							i += standardType.numLeaves();

							Semicolon semicolon = new Semicolon( all_tokens, i );

							if( semicolon.accepted() ){

								children.add( semicolon );

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
		
	private boolean tryFunctionIdArgumentsSemicolon( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Function function = new Function( all_tokens, i );

		if( function.accepted() ){

			i += function.numLeaves();

			children.add( function );

			ID id = new ID( all_tokens, i );

			if( id.accepted() ){

				i += id.numLeaves();

				children.add( id );

				Arguments arguments = new Arguments( all_tokens, i );

				if( arguments.accepted() ){

					children.add( arguments );

					i += arguments.numLeaves();

					Semicolon semicolon = new Semicolon( all_tokens, i );

					if( semicolon.accepted() ){

						children.add( semicolon );

						success = true;

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

		System.out.println( "subprogram head. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	SubprogramHead( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryFunctionIdArgumentsColonStandardTypeSemicolon( all_tokens, i ) ){

			didAccept = true;

		}else if( tryFunctionIdArgumentsSemicolon( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


