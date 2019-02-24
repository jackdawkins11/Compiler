
package Parser;
import java.util.Vector;

import Scanner.*;

public class Type extends Node{
	
	private boolean tryArrayParenNumColonNumParenOfStandardType( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Array array = new Array( all_tokens, i );

		if( array.accepted() ){

			children.add( array );

			i += array.numLeaves();

			LSParen lsParen = new LSParen( all_tokens, i );

			if( lsParen.accepted() ){

				children.add( lsParen );

				i += lsParen.numLeaves();

				Num num1 = new Num( all_tokens, i );

				if( num1.accepted() ){

					i += num1.numLeaves();

					children.add( num1 );

					Colon colon = new Colon( all_tokens, i );

					if( colon.accepted() ){

						i += colon.numLeaves();

						children.add( colon );

						Num num2 = new Num( all_tokens, i );

						if( num2.accepted() ){

							i += num2.numLeaves();

							children.add( num2 );

							RSParen rsParen = new RSParen( all_tokens, i );

							if( rsParen.accepted() ){

								i += rsParen.numLeaves();

								children.add( rsParen );

								Of of = new Of( all_tokens, i );

								if( of.accepted() ){

									i += of.numLeaves();

									children.add( of );

									StandardType standardType = new StandardType( all_tokens, i );

									if( standardType.accepted() ){

										children.add( standardType );

										success = true;

									}

								}

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
	
	private boolean tryStandardType( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		StandardType standardType = new StandardType( all_tokens, i );

		if( standardType.accepted() ){

			children.add( standardType );

			success = true;

		}

		if( !success ){

			children.clear();

		}

		return success;

	}

				

	@Override
	public void print(){

		System.out.println( "type. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	Type( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryArrayParenNumColonNumParenOfStandardType( all_tokens, i ) ){

			didAccept = true;

		}else if( tryStandardType( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


