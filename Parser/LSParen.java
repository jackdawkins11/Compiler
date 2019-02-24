
package Parser;
import java.util.Vector;

import Scanner.*;

public class LSParen extends Node{

	Token token;

	int loc;

	private boolean tryLSParen( final Vector< Token > all_tokens, final int i ){

		if( all_tokens.size() <= i ){

			return false;

		}
		
		boolean success = all_tokens.get( i ).getType() == EnumToken.LSPAREN ;

		if( success ){

			token = new Token( all_tokens.get( i ).getType(), all_tokens.get( i ).getString() );

		}

		return success;
	}

	@Override
	public void print(){

		System.out.println( loc + " Left square paran " );

	}


	LSParen( final Vector< Token > all_tokens, final int i ){

		isTerminal = true;

		loc = i;
		
		if( tryLSParen( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


