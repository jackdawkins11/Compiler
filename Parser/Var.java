
package Parser;
import java.util.Vector;

import Scanner.*;

public class Var extends Node{

	Token token;

	int loc;

	private boolean tryVar( Vector< Token > all_tokens, int i ){

		if( all_tokens.size() <= i ){

			return false;

		}

		boolean success  =all_tokens.get( i ).getType() == EnumToken.VAR;

		if( success ){

			token = new Token( all_tokens.get( i ).getType(), all_tokens.get( i ).getString() );

		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + " Var " + token.getString() );


	}


	Var( Vector< Token > all_tokens, int i ){

		isTerminal = true;

		loc = i;
		
		if( tryVar( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


