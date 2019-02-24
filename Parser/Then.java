
package Parser;
import java.util.Vector;

import Scanner.*;

public class Then extends Node{

	Token token;

	int loc;

	private boolean tryThen( Vector< Token > all_tokens, int i ){

		if( all_tokens.size() <= i ){

			return false;

		}

		boolean success  =all_tokens.get( i ).getType() == EnumToken.THEN;

		if( success ){

			token = new Token( all_tokens.get( i ).getType(), all_tokens.get( i ).getString() );

		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + " Then " + token.getString() );


	}


	Then( Vector< Token > all_tokens, int i ){

		isTerminal = true;

		loc = i;
		
		if( tryThen( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


