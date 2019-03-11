
package scanner;

import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.StringBufferInputStream;

/*
 *
 * Simple wrapper around the scanner created by 
 * JFlex. Gives a constructor for filenames and
 * string buffers. Also adds a "hasNext()" function.
 */

public class MyScanner{

	JFlexScanner JFlexScanner1;

	Token the_next_token;

	public MyScanner( String stringOrFilename, boolean isFilename ){

		InputStreamReader isr = null;

		if( isFilename ){	

			FileInputStream fis = null;

			try{

				fis = new FileInputStream( stringOrFilename );

			}catch( Exception e ){ e.printStackTrace(); }

			isr = new InputStreamReader( fis );
		
		}else{

			StringBufferInputStream sbis = new StringBufferInputStream( stringOrFilename );

			isr = new InputStreamReader( sbis );

		}

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
