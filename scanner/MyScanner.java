
package scanner;

import java.io.InputStreamReader;

/*
 *
 * Simple wrapper around the scanner created by 
 * JFlex that handles the possible 
 * expection in the constructor
 * and implements the useful "hasNext()" function.
 */

public class MyScanner{

	JFlexScanner JFlexScanner1;

	Token the_next_token;

	public MyScanner( InputStreamReader isr ){

		JFlexScanner1 = new JFlexScanner( isr );

		try{

			the_next_token = JFlexScanner1.next_token();

		}catch( Exception e ){ e.printStackTrace(); }
	
	}

	public boolean hasNext(){

		return the_next_token.getType() != EnumToken.EOF;

	}

	public Token next(){

		Token old_token = the_next_token;

		try{

			the_next_token = JFlexScanner1.next_token();

		}catch( Exception e ){ e.printStackTrace(); }

		return old_token;

	}

}
