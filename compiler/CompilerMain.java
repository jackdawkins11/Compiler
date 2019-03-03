
package compiler;

import parser.*;

class CompilerMain{

	public static void main( String args[] ){

		if( args.length != 1 ){

			System.out.println("This takes in a single file name and tries to parse the file.");

			assert(false);

		}

		Recognizer recognizer = new Recognizer( args[ 0 ] );

		if( recognizer.programNT() ){

			System.out.println("The input is a MicroPascal program. Printing symbol table.");

			recognizer.printTable();

		}else{

			System.out.println("The input is not a MicroPascal program.");

		}

	}

}
