
package Parser;
import java.util.Vector;

import Scanner.*;

public class Do extends Node{

	Token token;

	int loc;

	private boolean tryDo( Vector< Token > all_tokens, int i ){

		if( all_tokens.size() <= i ){

			return false;

		}

		boolean success  =all_tokens.get( i ).getType() == EnumToken.DO;

		if( success ){

			token = new Token( all_tokens.get( i ).getType(), all_tokens.get( i ).getString() );

		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + " Do " + token.getString() );


	}


	Do( Vector< Token > all_tokens, int i ){

		isTerminal = true;

		loc = i;
		
		if( tryDo( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


