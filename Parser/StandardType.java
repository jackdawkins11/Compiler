package Parser;

import java.util.Vector;

import Scanner.*;

public class StandardType extends Node{
	
	private boolean tryInteger( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Integer integer = new Integer( all_tokens, i );

		if( integer.accepted() ){

			children.add( integer );

			success = true;

		}

		if( !success ){

			children.clear();

		}

		return success;
	
	}
	
	private boolean tryReal( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Real real = new Real( all_tokens, i );

		if( real.accepted() ){

			children.add( real );

			success = true;

		}

		if( !success ){

			children.clear();

		}

		return success;
	
	}
	
	@Override
	public void print(){

		System.out.println( "standard type. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	StandardType( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryInteger( all_tokens, i ) ){

			didAccept = true;

		}else if( tryReal( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


