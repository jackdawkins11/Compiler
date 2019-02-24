
package Parser;
import java.util.Vector;

import Scanner.*;

public class Program extends Node{

	Token token;

	int loc;

	private boolean tryProgram( Vector< Token > all_tokens, int i ){

		if( all_tokens.size() <= i ){

			return false;

		}

		boolean success = all_tokens.get( i ).getType() == EnumToken.PROGRAM;

		if( success ){

			token = new Token( all_tokens.get( i ).getType(), all_tokens.get( i ).getString() );

		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + " Program " + token.getString() );


	}

	Program( Vector< Token > all_tokens, int i ){

		isTerminal = true;

		loc = i;
		
		if( tryProgram( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


