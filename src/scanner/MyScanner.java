
package scanner;

import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;

/*
 * This class is mainly a wrapper
 * around the scanner JFlex created.
 */

public class MyScanner{

	//////////////////
	//     Data     //
	//////////////////

	JFlexScanner JFlexScanner1;

	Token the_next_token;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public MyScanner( String stringOrFilename, boolean isFilename ){

		InputStreamReader isr = null;

		if( isFilename ){

			FileInputStream fis = null;

			try{

				fis = new FileInputStream( stringOrFilename );

			}catch( Exception e ){ e.printStackTrace(); }

			isr = new InputStreamReader( fis );

		}else{

			ByteArrayInputStream bais = null;

			try{

				bais = new ByteArrayInputStream( stringOrFilename.getBytes( "UTF-8" ) );

			}catch( Exception e ){ e.printStackTrace(); }

			isr = new InputStreamReader( bais );

		}

		JFlexScanner1 = new JFlexScanner( isr );

		try{

			the_next_token = JFlexScanner1.next_token();

		}catch( Exception e ){ e.printStackTrace(); }
	
	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////
	
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
