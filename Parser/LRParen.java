
package Parser;
import java.util.Vector;

import Scanner.*;

public class LRParen extends Node{

	Token token;

	int loc;

	private boolean tryLRParen( final Vector< Token > all_tokens, final int i ){

		if( all_tokens.size() <= i ){

			return false;

		}
		
		boolean success = all_tokens.get( i ).getType() == EnumToken.LRPAREN ;

		if( success ){

			token = new Token( all_tokens.get( i ).getType(), all_tokens.get( i ).getString() );

		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + "left round paran " );

	}


	LRParen( final Vector< Token > all_tokens, final int i ){

		isTerminal = true;

		loc = i;
		
		if( tryLRParen( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


