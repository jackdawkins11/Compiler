
package Parser;
import java.util.Vector;

import Scanner.*;

public class Sign extends Node{

	Token token;

	int loc;

	private boolean trySign( Vector< Token > all_tokens, int i ){

		if( all_tokens.size() <= i ){

			return false;

		}
		
		boolean success = all_tokens.get( i ).getString().equals("+") || all_tokens.get( i ).getString().equals("-");

		if( success ){

			token = new Token( EnumToken.SIGN, all_tokens.get( i ).getString() );
		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + " sign " + token.getString()  );

	}


	Sign( Vector< Token > all_tokens, int i ){

		isTerminal = true;

		loc = i;

		if( trySign( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


