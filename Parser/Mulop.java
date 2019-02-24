
package Parser;
import java.util.Vector;

import Scanner.*;

public class Mulop extends Node{

	Token token;

	int loc;

	private boolean tryMulop( Vector< Token > all_tokens, int i ){

		if( all_tokens.size() <= i ){

			return false;

		}
		
		boolean success = all_tokens.get( i ).getType() == EnumToken.MULOP ;

		if( success ){

			token = new Token( all_tokens.get( i ).getType(), all_tokens.get( i ).getString() );

		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + " mulop " + token.getString() );

	}


	Mulop( Vector< Token > all_tokens, int i ){

		isTerminal = true;

		loc = i;
		
		if( tryMulop( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


