
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.StringBufferInputStream;
import java.util.Scanner;
import scanner.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TestScanner{
	
	static boolean testTheScanner( String stringOrFilenameA, String stringOrFilenameB, boolean isFilename ){

		/* step one: create the scanners */

		MyScanner tokenScanner = new MyScanner( stringOrFilenameA, isFilename );

		Scanner annotatedTokenScanner = null;

		if( isFilename ){

			FileInputStream fis = null;

			try{
				
				fis = new FileInputStream( stringOrFilenameB );	

			}catch( Exception e ){ e.printStackTrace(); }

			InputStreamReader isr = new InputStreamReader( fis );

			annotatedTokenScanner = new Scanner( isr );

		}else{
	
			StringBufferInputStream sbis = new StringBufferInputStream( stringOrFilenameB );	

			InputStreamReader isr = new InputStreamReader( sbis );

			annotatedTokenScanner = new Scanner( isr );

		}
		
		/* step two: start getting tokens
		 * from tokenScanner and assert
		 * it equals the annotated version.
		 */

		boolean success = true;

		while( tokenScanner.hasNext() && success ){

			Token token = tokenScanner.next();

			String correctLexeme = null,
			       correctType= null;

			if( annotatedTokenScanner.hasNext() ){

				correctLexeme = annotatedTokenScanner.next();

				if( annotatedTokenScanner.hasNext() ){
			
					correctType = annotatedTokenScanner.next();

				}

			}

			success = correctLexeme != null
				&& correctType != null;

			if( success ){

				success = token.getType().toString().equals( correctType )
					&& token.getString().equals( correctLexeme );

			}

		}

		return success;

	}

	@Test 
	public void runTests(){

		assertTrue( testTheScanner( "testData/scannerTestData/tokens1.txt",
				       "testData/scannerTestData/annotatedTokens1.txt",
			       true ) );
		
		assertTrue( testTheScanner( "testData/scannerTestData/tokens2.txt",
				       "testData/scannerTestData/annotatedTokens2.txt",
			       true ) );

		String tokenString1 = 
			"this program var ) [ := ";

		String annotatedTokenString1 = 
			"this ID program PROGRAM var VAR ) RRPAREN [ LSPAREN := ASSIGNOP";

		assertTrue( testTheScanner( tokenString1, annotatedTokenString1,
					false ) );
	
		String uncorrectAnnotatedTokenString1 =	
			"this VAR program PROGRAM var VAR ) RRPAREN [ LSPAREN := ASSIGNOP";
	
		assertFalse( testTheScanner( tokenString1, uncorrectAnnotatedTokenString1,
					false ) );
	
	}
	
}
