
package compiler;

import scanner.MyScanner;
import parser.Parser;
import syntaxTree.ProgramNode;
import java.io.PrintWriter;

public class CompilerMain{

	public static void main( String args[] ){

		if( args.length != 1 ){

			System.out.println( "Error expected file name in arguments." );

			System.exit( 1 );

		}

		MyScanner scanner = new MyScanner( args[ 0 ], true );

		Parser parser = new Parser( scanner );

		ProgramNode programNode = null;

		programNode = parser.program();

		PrintWriter output = null;
		
		try{
			output = new PrintWriter( "output.asm" );

		}catch( Exception e ){

			System.out.println("error creating output file");

			System.exit( 1 );

		}

		String code = programNode.toMips();

		output.println( code );

		output.close();

		
	}

}

