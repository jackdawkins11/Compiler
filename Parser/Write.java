
package Parser;
import java.util.Vector;

import Scanner.*;

public class Write extends Node{

	Token token;

	int loc;

	private boolean tryWrite( Vector< Token > all_tokens, int i ){

		if( all_tokens.size() <= i ){

			return false;

		}

		boolean success  =all_tokens.get( i ).getType() == EnumToken.WRITE;

		if( success ){

			token = new Token( all_tokens.get( i ).getType(), all_tokens.get( i ).getString() );

		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + " Write " + token.getString() );


	}


	Write( Vector< Token > all_tokens, int i ){

		isTerminal = true;

		loc = i;
		
		if( tryWrite( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


