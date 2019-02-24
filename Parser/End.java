
package Parser;
import java.util.Vector;

import Scanner.*;

public class End extends Node{

	Token token;

	int loc;

	private boolean tryEnd( Vector< Token > all_tokens, int i ){

		if( all_tokens.size() <= i ){

			return false;

		}

		boolean success  =all_tokens.get( i ).getType() == EnumToken.END;

		if( success ){

			token = new Token( all_tokens.get( i ).getType(), all_tokens.get( i ).getString() );

		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + " End " + token.getString() );


	}


	End( Vector< Token > all_tokens, int i ){

		isTerminal = true;

		loc = i;
		
		if( tryEnd( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


