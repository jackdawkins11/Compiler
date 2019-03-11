
package compiler;

import parser.*;
import scanner.*;

class CompilerMain{

	public static void main( String args[] ){

		if( args.length != 1 ){

			System.out.println("This takes in a single file name and tries to parse the file.");

			assert(false);

		}

		MyScanner myScanner = new MyScanner( args[ 0 ], true );

		Recognizer recognizer= new Recognizer( myScanner );

		if( recognizer.programNT() ){

			System.out.println("The input is a MicroPascal program. Printing symbol table.");

			recognizer.printTable();

		}else{

			System.out.println("The input is not a MicroPascal program.");

		}

	}

}
