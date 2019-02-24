
package Parser;
import java.util.Vector;

import Scanner.*;

public class Relop extends Node{

	Token token;

	int loc;

	private boolean tryRelop( final Vector< Token > all_tokens, int i ){

		if( all_tokens.size() <= i ){

			return false;

		}
		
		boolean success = all_tokens.get( i ).getType() == EnumToken.RELOP ;

		if( success ){

			token = new Token( all_tokens.get( i ).getType(), all_tokens.get( i ).getString() );

		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + " relop  " + token.getString() );

	}


	Relop( final Vector< Token > all_tokens, final int i ){

		isTerminal = true;

		loc = i;
		
		if( tryRelop( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


