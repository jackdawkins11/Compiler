
package Parser;

import java.util.Vector;

import Scanner.*;

public class Arguments extends Node{
	
	private boolean tryParenParameterListParen( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		LRParen lrParen = new LRParen( all_tokens, i );

		if( lrParen.accepted() ){

			children.add( lrParen );

			i += lrParen.numLeaves();

			ParameterList parameterList = new ParameterList( all_tokens, i );

			if( parameterList.accepted() ){

				i += parameterList.numLeaves();

				children.add( parameterList );

				RRParen rrParen = new RRParen( all_tokens, i );

				if( rrParen.accepted() ){

					children.add( rrParen );

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

		System.out.println( "arguments. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	Arguments( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryParenParameterListParen( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = true;

		}

	}

}


