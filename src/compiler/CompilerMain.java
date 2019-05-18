
package compiler;

import scanner.MyScanner;
import parser.Parser;
import syntaxTree.ProgramNode;
import java.io.PrintWriter;

/*
 * This is the entry-point
 * for the compiler. It reads
 * a string from the command,
 * compiles the program
 * specified in that string and
 * then writes the generated
 * mips code to a file
 * called "output.asm".
 */

public class CompilerMain{

	public static void main( String args[] ){

		/*
		 * Check for too few parameters.
		 */

		if( args.length != 1 ){

			System.out.println( "Error expected file name in arguments." );

			System.exit( 1 );

		}

		/*
		 * Create scanner on the file specified.
		 */

		MyScanner scanner = new MyScanner( args[ 0 ], true );

		/*
		 * Create parser with the scanner.
		 */

		Parser parser = new Parser( scanner );

		/*
		 * Create the syntax tree. Store
		 * the root as programNode.
		 */

		ProgramNode programNode = parser.program();

		/*
		 * Open the output file.
		 */

		PrintWriter output = null;
		
		try{
			output = new PrintWriter( "output.asm" );

		}catch( Exception e ){

			System.out.println("error creating output file");

			System.exit( 1 );

		}

		/*
		 * Generate and get the mips code.
		 */

		String code = programNode.toMips();

		/*
		 * Write the code to "output.asm".
		 */

		output.println( code );

		output.close();
		
	}

}

