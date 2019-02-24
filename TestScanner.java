
/* stream from a file */

import java.io.FileInputStream;

import java.io.InputStreamReader;

/* scan for tokens seperated by whitespace */

import java.util.Scanner;

/* MyScanner */

import Scanner.*;

/* JUnit */

import org.junit.*;
import static org.junit.Assert.*;

public class TestScanner{
	
	static boolean testTheScanner( String args[] ){

		if( args.length != 2 ){

			System.out.println("usage: <file with pascal code> <file with the token seperated by space> ");

			return false;

		}

		/* open files */

		//code

		FileInputStream code_fis = null;

		try{

			code_fis = new FileInputStream( args[0] );

		}catch (Exception e ){ e.printStackTrace(); }

		InputStreamReader code_isr = new InputStreamReader( code_fis );

		//tokens

		FileInputStream token_fis = null;

		try{

			token_fis = new FileInputStream( args[1] );

		}catch (Exception e ){ e.printStackTrace(); }


		/* create the scanners */

		MyScanner code = new MyScanner( code_isr );

		Scanner ans = new Scanner( token_fis );

		/* set token/string to null */

		Token token = new Token( EnumToken.EOF, "" );

		String ans_string = null;
		
		String ans_type = null;
			
		boolean is_first_iteration = true;

		boolean end_of_file = false;

		boolean good_so_far = true;
		
		while( is_first_iteration || ( !end_of_file && good_so_far ) ){
			
			//do they have another token?

			boolean ans_has_next, code_has_next;

			if( code.hasNext() ){

				token = code.next();

				code_has_next = true;

			}else{

				code_has_next = false;

			}

			if( ans.hasNext() ){

				ans_string = ans.next();

				if( ans.hasNext() ){

					ans_type = ans.next();

					ans_has_next = true;

				}else{

					ans_has_next = false;

				}

			}else{

				ans_has_next = false;

			}

			//true if either scanner ran out

			end_of_file = !code_has_next || !ans_has_next;

			if( end_of_file ){

				//System.out.println("end of file");
				
				//did only one scanner end?
				
				good_so_far = code_has_next == ans_has_next;

			}else{

				//System.out.println("\"" + token.getString() + "\" == \"" + ans_string 
				//		+ "\" and " + token.getType().toString() + " == " + ans_type );

				good_so_far = ans_string.equals( token.getString() ) 
					&& ans_type.equals( token.getType().toString() );

			}

			is_first_iteration = false;
		
		}

		if( good_so_far ){

			return true;

		}else{

			return false;

		}

	}

	@Test 
	public void runTests(){

		String args[] = new String[2];

		args[ 0 ] = "test_data/code_1.txt";
		args[ 1 ] = "test_data/correct_1.txt";

		assertTrue( testTheScanner( args ) );

		args[ 0 ] = "test_data/code_2.txt";
		args[ 1 ] = "test_data/correct_2.txt";

		assertTrue( testTheScanner( args ) );

	}
	
}
