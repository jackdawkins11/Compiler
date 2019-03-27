
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Scanner;
import scanner.*;
import parser.Parser;
import syntaxTree.*;

public class TestRecognizer{

	@Test
	public void testOne(){

		String program = 
				"program foo;\n"
			+	"var fee, fi, fo, fum : integer;\n"
			+	"begin\n"
			+		"fee := 4;\n"
			+		"fi := 5;\n"
			+		"fo := 3 * fee + fi;\n"
			+		"if fo < 13\n"
			+		"then\n"
			+			"fo := 13\n"
			+		"else\n"
			+			"fo := 26\n"
			+		";\n"	
			+		"write( fo )\n"
			+	"end\n"
			+	".\n"
			;

		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		boolean exceptionWasThrown = false;

		try{

			ProgramNode programNode = parser.program();

		} catch( Exception e ){

			exceptionWasThrown = true;

		}

		assertFalse( exceptionWasThrown );

	}

	@Test
	public void testTwo(){

		String program = 
				"program foo;\n"
			+	"var fee, fi, fo, fum : integer;\n"
			+	"function funct ( x, y, z : real ) : integer;\n"
			+	"var a, b, c : real;\n"
			+	"begin\n"
			+		"a := x * y + x\n"
			+	"end\n"
			+	";\n"
			+	"begin\n"
			+		"fee := 4;\n"
			+		"fi := 5;\n"
			+		"fo := 3 * fee + fi;\n"
			+		"if fo < 13\n"
			+		"then\n"
			+			"fo := 13\n"
			+		"else\n"
			+			"fo := 26\n"
			+		";\n"	
			+		"write( fo );\n"
			+		"fee := funct( fee, 3, fi);\n"
			+		"funct\n"
			+	"end\n"
			+	".\n"
			;
		
		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		boolean exceptionWasThrown = false;

		try{

			ProgramNode programNode = parser.program();

		} catch( Exception e ){

			exceptionWasThrown = true;

		}

		assertFalse( exceptionWasThrown );

	}


	@Test
	public void testThree(){

		String program = 
				"program foo;\n"
			+	"var fee, fi, fo, fum : integer;\n"
			+	"begin\n"
			+		"fee := 4;\n"
			+		"fi := 5;\n"
			+		"fo := 3  fee + fi;\n"
			+		"if fo < 13\n"
			+		"then\n"
			+			"fo := 13\n"
			+		"else\n"
			+			"fo := 26\n"
			+		";\n"	
			+		"write( fo )\n"
			+	"end\n"
			+	".\n"
			;

		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		boolean exceptionWasThrown = false;

		try{

			ProgramNode programNode = parser.program();

		} catch( Exception e ){

			exceptionWasThrown = true;

		}

		assertTrue( exceptionWasThrown );

	}

	@Test
	public void testFour(){

		String program = 
				"program foo;\n"
			+	"var fee, fi, fo, fum : integer;\n"
			+	"function funct ( x, y, z : real ) : integer;\n"
			+	"var a, b, c : real;\n"
			+	"begin\n"
			+		"a := x * y + x\n"
			+	"end\n"
			+	";\n"
			+	"begin\n"
			+		"fee := 4;\n"
			+		"fi := 5;\n"
			+		"fo := 3 * fee + fi;\n"
			+		"if fo < 13\n"
			+		"then\n"
			+			"fo := 13\n"
			+		"else\n"
			+			"fo := 26\n"
			+		";\n"	
			+		"write( fo );\n"
			+		"fee := funct( fee 3, fi);\n"
			+		"funct\n"
			+	"end\n"
			+	".\n"
			;
		
		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		boolean exceptionWasThrown = false;

		try{

			ProgramNode programNode = parser.program();

		} catch( Exception e ){

			exceptionWasThrown = true;

		}

		assertTrue( exceptionWasThrown );

	}

	/*
	 * Make sure variables
	 * are delcared before
	 * they are used.
	 */

	@Test
	public void testFive(){

		String program = 
				"program foo;\n"
			+	"var fee, fi, fo, fum : integer;\n"
			+	"function funct ( x, y, z : real ) : integer;\n"
			+	"var a, b, c : real;\n"
			+	"begin\n"
			+		"a := x * y + x\n"
			+	"end\n"
			+	";\n"
			+	"begin\n"
			+		"fee := 4;\n"
			+		"fi := 5;\n"
			+		"fo := 3 * fee + fi;\n"
			+		"if fo < 13\n"
			+		"then\n"
			+			"fo := 13\n"
			+		"else\n"
			+			"fo := 26\n"
			+		";\n"	
			+		"write( fo );\n"
			+		"fee := funct( feer, 3, fi);\n"
			+		"funct\n"
			+	"end\n"
			+	".\n"
			;
		
		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		boolean exceptionWasThrown = false;

		try{

			ProgramNode programNode = parser.program();

		} catch( Exception e ){

			exceptionWasThrown = true;

		}

		assertTrue( exceptionWasThrown );

	}

	/*
	 * Make sure types match
	 * across assignment.
	 */

	@Test
	public void testSix(){

		String program = 
				"program foo;\n"
			+	"var fee, fi, fo, fum : integer;\n"
			+	"function funct ( x, y, z : real ) : integer;\n"
			+	"var a, b, c : real;\n"
			+	"begin\n"
			+		"a := x * y + x\n"
			+	"end\n"
			+	";\n"
			+	"begin\n"
			+		"fee := 4.3;\n"
			+		"fi := 5;\n"
			+		"fo := 3 * fee + fi;\n"
			+		"if fo < 13\n"
			+		"then\n"
			+			"fo := 13\n"
			+		"else\n"
			+			"fo := 26\n"
			+		";\n"	
			+		"write( fo );\n"
			+		"fee := funct( fee, 3, fi);\n"
			+		"funct\n"
			+	"end\n"
			+	".\n"
			;
		
		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		boolean exceptionWasThrown = false;

		try{

			ProgramNode programNode = parser.program();

		} catch( Exception e ){

			exceptionWasThrown = true;

		}

		assertTrue( exceptionWasThrown );

	}

	/*
	 * Make sure functions
	 * are declared before
	 * used.
	 */

	@Test
	public void testSeven(){

		String program = 
				"program foo;\n"
			+	"var fee, fi, fo, fum : integer;\n"
			+	"function funct ( x, y, z : real ) : integer;\n"
			+	"var a, b, c : real;\n"
			+	"begin\n"
			+		"a := x * y + x\n"
			+	"end\n"
			+	";\n"
			+	"begin\n"
			+		"fee := 4;\n"
			+		"fi := 5;\n"
			+		"fo := 3 * fee + fi;\n"
			+		"if fo < 13\n"
			+		"then\n"
			+			"fo := 13\n"
			+		"else\n"
			+			"fo := 26\n"
			+		";\n"	
			+		"write( fo );\n"
			+		"fee := funt( fee, 3, fi);\n"
			+		"funct\n"
			+	"end\n"
			+	".\n"
			;
		
		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		boolean exceptionWasThrown = false;

		try{

			ProgramNode programNode = parser.program();

		} catch( Exception e ){

			exceptionWasThrown = true;

		}

		assertTrue( exceptionWasThrown );

	}

}

