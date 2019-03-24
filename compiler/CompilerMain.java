
package compiler;

import scanner.MyScanner;
import parser.Parser;
import syntaxTree.ProgramNode;

public class CompilerMain{

	public static void main( String args[] ){

		MyScanner scanner = new MyScanner( args[ 0 ], true );

		Parser parser = new Parser( scanner );

		ProgramNode programNode = null;

		try{

			programNode = parser.program();

		}catch( Exception e ){

			System.out.println("Exception: " + e.getMessage() );

			System.exit( 1 );

		}
	
		parser.printSymbolTable();

		System.out.println("Indented to string: \n" + programNode.indentedToString( 0 ) );		

	}

}

