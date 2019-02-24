
package Parser;

import java.util.Vector;

import Scanner.*;

public class Addop extends Node{

	Token token;

	int loc;

	private boolean tryAddop( Vector< Token > all_tokens, int i ){

		if( all_tokens.size() <= i ){

			return false;

		}

		boolean success  = all_tokens.get( i ).getType() == EnumToken.ADDOP || all_tokens.get( i ).getType() == EnumToken.SIGN;

		if( success ){

			token = new Token( EnumToken.ADDOP, all_tokens.get( i ).getString() );

		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + " Addop " + token.getString() );


	}

	Addop( Vector< Token > all_tokens, int i ){

		isTerminal = true;

		loc = i;
		
		if( tryAddop( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


