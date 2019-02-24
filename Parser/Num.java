
package Parser;
import java.util.Vector;

import Scanner.*;

public class Num extends Node{

	Token token;

	int loc;

	private boolean tryNum( final Vector< Token > all_tokens, final int i ){

		if( all_tokens.size() <= i ){

			return false;

		}
		
		boolean success = all_tokens.get( i ).getType() == EnumToken.NUM ;

		if( success ){

			token = new Token( all_tokens.get( i ).getType(), all_tokens.get( i ).getString() );

		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + " num " + token.getString()  );

	}


	Num( final Vector< Token > all_tokens, final int i ){

		isTerminal = true;

		loc = i;

		if( tryNum( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


