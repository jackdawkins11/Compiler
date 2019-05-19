
import org.junit.*;
import static org.junit.Assert.*;
import scanner.*;
import parser.Parser;
import syntaxTree.*;

public class TestParser{

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
	
		ProgramNode root = parser.program();

		System.out.println( root.indentedToString( 0 ) );
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
			+		"funct(1, 2, 4)\n"
			+	"end\n"
			+	".\n"
			;
		
		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		ProgramNode root = parser.program();

		System.out.println( root.indentedToString( 0 ) );
	
	}


	@Test
	public void testThree(){

		String program = 
				"program foo;\n"
			+	"var fee, fi, fo, fum : integer;\n"
			+	"begin\n"
			+		"fee := 4;\n"
			+		"fi := 5;\n"
			+		"fo := 3 + fee + fi;\n"
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

		ProgramNode root = parser.program();

		System.out.println( root.indentedToString( 0 ) );
	
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
			+		"fee := funct( fee, 3, fi);\n"
			+		"funct\n"
			+	"end\n"
			+	".\n"
			;
		
		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		ProgramNode root = parser.program();

		System.out.println( root.indentedToString( 0 ) );
	
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
			+		"fee := funct( fee, 3, fi);\n"
			+		"funct( 1, 2, 3)\n"
			+	"end\n"
			+	".\n"
			;
		
		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		ProgramNode root = parser.program();

		System.out.println( root.indentedToString( 0 ) );
	
	}
	
	/*
	 * Make sure functions
	 * are declared before
	 * used.
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
			+		"funct(1, 2, 4)\n"
			+	"end\n"
			+	".\n"
			;

			String expected = 	
				"ProgramNode. Name: foo\n"
			+	"|-- DeclarationsNode.\n"
			+	"|-- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- VariableNode. Name: fum Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- SubProgramDeclarationsNode.\n"
			+	"|-- --- SubProgramNode. Name: funct\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- --- VariableNode. Name: a Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- VariableNode. Name: b Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- VariableNode. Name: c Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- VariableNode. Name: y Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- VariableNode. Name: z Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- CompoundStatementNode.\n"
			+	"|-- --- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- --- VariableNode. Name: a Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- --- OperationExpressionNode. Operation: + Standard Type: REAL\n"
			+	"|-- --- --- --- --- --- OperationExpressionNode. Operation: * Standard Type: REAL\n"
			+	"|-- --- --- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL\n"
			+	"|-- --- --- --- --- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL\n"
			+	"|-- --- --- --- --- --- --- --- VariableNode. Name: y Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL\n"
			+	"|-- --- --- --- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- CompoundStatementNode.\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 4 Standard Type: INTEGER\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 5 Standard Type: INTEGER\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- OperationExpressionNode. Operation: + Standard Type: INTEGER\n"
			+	"|-- --- --- --- OperationExpressionNode. Operation: * Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 3 Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- IfThenStatementNode.\n"
			+	"|-- --- --- OperationExpressionNode. Operation: < Standard Type: INTEGER\n"
			+	"|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 13 Standard Type: INTEGER\n"
			+	"|-- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 13 Standard Type: INTEGER\n"
			+	"|-- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 26 Standard Type: INTEGER\n"
			+	"|-- --- WriteStatementNode.\n"
			+	"|-- --- --- VariableValueExpressionNode. Standard Type: INTEGER\n"
			+	"|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- SubProgramValueExpressionNode. Name: funct Standard Type: INTEGER\n"
			+	"|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 3 Standard Type: INTEGER\n"
			+	"|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- SubProgramStatementNode. Name: funct\n"
			;
		
				
		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		ProgramNode root = parser.program();

		assertEquals( root.indentedToString( 0 ), expected );
	
	}

}
/*
|-- DeclarationsNode.
|-- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fum Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- SubProgramDeclarationsNode.
|-- --- SubProgramNode. Name: funct
|-- --- --- DeclarationsNode.
|-- --- --- --- VariableNode. Name: a Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- VariableNode. Name: b Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- VariableNode. Name: c Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- DeclarationsNode.
|-- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- VariableNode. Name: y Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- VariableNode. Name: z Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- CompoundStatementNode.
|-- --- --- --- VariableAssignmentStatementNode.
|-- --- --- --- --- VariableNode. Name: a Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- --- OperationExpressionNode. Operation: + Standard Type: REAL
|-- --- --- --- --- --- OperationExpressionNode. Operation: * Standard Type: REAL
|-- --- --- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL
|-- --- --- --- --- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL
|-- --- --- --- --- --- --- --- VariableNode. Name: y Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL
|-- --- --- --- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1
|-- CompoundStatementNode.
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- NumValueExpressionNode. Num: 4 Standard Type: INTEGER
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- NumValueExpressionNode. Num: 5 Standard Type: INTEGER
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- OperationExpressionNode. Operation: + Standard Type: INTEGER
|-- --- --- --- OperationExpressionNode. Operation: * Standard Type: INTEGER
|-- --- --- --- --- NumValueExpressionNode. Num: 3 Standard Type: INTEGER
|-- --- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- IfThenStatementNode.
|-- --- --- OperationExpressionNode. Operation: < Standard Type: INTEGER
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 13 Standard Type: INTEGER
|-- --- --- VariableAssignmentStatementNode.
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 13 Standard Type: INTEGER
|-- --- --- VariableAssignmentStatementNode.
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 26 Standard Type: INTEGER
|-- --- WriteStatementNode.
|-- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- SubProgramValueExpressionNode. Name: funct Standard Type: INTEGER
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 3 Standard Type: INTEGER
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- SubProgramStatementNode. Name: funct
|-- --- --- NumValueExpressionNode. Num: 1 Standard Type: INTEGER
|-- --- --- NumValueExpressionNode. Num: 2 Standard Type: INTEGER
|-- --- --- NumValueExpressionNode. Num: 3 Standard Type: INTEGER

.ProgramNode. Name: foo
|-- DeclarationsNode.
|-- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fum Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- SubProgramDeclarationsNode.
|-- --- SubProgramNode. Name: funct
|-- --- --- DeclarationsNode.
|-- --- --- --- VariableNode. Name: a Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- VariableNode. Name: b Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- VariableNode. Name: c Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- DeclarationsNode.
|-- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- VariableNode. Name: y Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- VariableNode. Name: z Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- CompoundStatementNode.
|-- --- --- --- VariableAssignmentStatementNode.
|-- --- --- --- --- VariableNode. Name: a Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- --- OperationExpressionNode. Operation: + Standard Type: REAL
|-- --- --- --- --- --- OperationExpressionNode. Operation: * Standard Type: REAL
|-- --- --- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL
|-- --- --- --- --- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL
|-- --- --- --- --- --- --- --- VariableNode. Name: y Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL
|-- --- --- --- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1
|-- CompoundStatementNode.
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- NumValueExpressionNode. Num: 4 Standard Type: INTEGER
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- NumValueExpressionNode. Num: 5 Standard Type: INTEGER
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- OperationExpressionNode. Operation: + Standard Type: INTEGER
|-- --- --- --- OperationExpressionNode. Operation: * Standard Type: INTEGER
|-- --- --- --- --- NumValueExpressionNode. Num: 3 Standard Type: INTEGER
|-- --- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- IfThenStatementNode.
|-- --- --- OperationExpressionNode. Operation: < Standard Type: INTEGER
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 13 Standard Type: INTEGER
|-- --- --- VariableAssignmentStatementNode.
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 13 Standard Type: INTEGER
|-- --- --- VariableAssignmentStatementNode.
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 26 Standard Type: INTEGER
|-- --- WriteStatementNode.
|-- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- SubProgramValueExpressionNode. Name: funct Standard Type: INTEGER
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 3 Standard Type: INTEGER
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- SubProgramStatementNode. Name: funct
|-- --- --- NumValueExpressionNode. Num: 1 Standard Type: INTEGER
|-- --- --- NumValueExpressionNode. Num: 2 Standard Type: INTEGER
|-- --- --- NumValueExpressionNode. Num: 4 Standard Type: INTEGER

.ProgramNode. Name: foo
|-- DeclarationsNode.
|-- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fum Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- SubProgramDeclarationsNode.
|-- CompoundStatementNode.
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- NumValueExpressionNode. Num: 4 Standard Type: INTEGER
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- NumValueExpressionNode. Num: 5 Standard Type: INTEGER
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- OperationExpressionNode. Operation: + Standard Type: INTEGER
|-- --- --- --- OperationExpressionNode. Operation: * Standard Type: INTEGER
|-- --- --- --- --- NumValueExpressionNode. Num: 3 Standard Type: INTEGER
|-- --- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- IfThenStatementNode.
|-- --- --- OperationExpressionNode. Operation: < Standard Type: INTEGER
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 13 Standard Type: INTEGER
|-- --- --- VariableAssignmentStatementNode.
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 13 Standard Type: INTEGER
|-- --- --- VariableAssignmentStatementNode.
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 26 Standard Type: INTEGER
|-- --- WriteStatementNode.
|-- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1

.ProgramNode. Name: foo
|-- DeclarationsNode.
|-- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fum Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- SubProgramDeclarationsNode.
|-- CompoundStatementNode.
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- NumValueExpressionNode. Num: 4 Standard Type: INTEGER
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- NumValueExpressionNode. Num: 5 Standard Type: INTEGER
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- OperationExpressionNode. Operation: + Standard Type: INTEGER
|-- --- --- --- NumValueExpressionNode. Num: 3 Standard Type: INTEGER
|-- --- --- --- OperationExpressionNode. Operation: + Standard Type: INTEGER
|-- --- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- IfThenStatementNode.
|-- --- --- OperationExpressionNode. Operation: < Standard Type: INTEGER
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 13 Standard Type: INTEGER
|-- --- --- VariableAssignmentStatementNode.
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 13 Standard Type: INTEGER
|-- --- --- VariableAssignmentStatementNode.
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 26 Standard Type: INTEGER
|-- --- WriteStatementNode.
|-- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1

.ProgramNode. Name: foo
|-- DeclarationsNode.
|-- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableNode. Name: fum Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- SubProgramDeclarationsNode.
|-- --- SubProgramNode. Name: funct
|-- --- --- DeclarationsNode.
|-- --- --- --- VariableNode. Name: a Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- VariableNode. Name: b Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- VariableNode. Name: c Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- DeclarationsNode.
|-- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- VariableNode. Name: y Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- VariableNode. Name: z Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- CompoundStatementNode.
|-- --- --- --- VariableAssignmentStatementNode.
|-- --- --- --- --- VariableNode. Name: a Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- --- OperationExpressionNode. Operation: + Standard Type: REAL
|-- --- --- --- --- --- OperationExpressionNode. Operation: * Standard Type: REAL
|-- --- --- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL
|-- --- --- --- --- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL
|-- --- --- --- --- --- --- --- VariableNode. Name: y Standard Type: REAL Begin Index: 0 End Index: 1
|-- --- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL
|-- --- --- --- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1
|-- CompoundStatementNode.
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- NumValueExpressionNode. Num: 4 Standard Type: INTEGER
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- NumValueExpressionNode. Num: 5 Standard Type: INTEGER
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- OperationExpressionNode. Operation: + Standard Type: INTEGER
|-- --- --- --- OperationExpressionNode. Operation: * Standard Type: INTEGER
|-- --- --- --- --- NumValueExpressionNode. Num: 3 Standard Type: INTEGER
|-- --- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- IfThenStatementNode.
|-- --- --- OperationExpressionNode. Operation: < Standard Type: INTEGER
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 13 Standard Type: INTEGER
|-- --- --- VariableAssignmentStatementNode.
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 13 Standard Type: INTEGER
|-- --- --- VariableAssignmentStatementNode.
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 26 Standard Type: INTEGER
|-- --- WriteStatementNode.
|-- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- VariableAssignmentStatementNode.
|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- SubProgramValueExpressionNode. Name: funct Standard Type: INTEGER
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- --- --- NumValueExpressionNode. Num: 3 Standard Type: INTEGER
|-- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER
|-- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1
|-- --- SubProgramStatementNode. Name: funct
|-- --- --- NumValueExpressionNode. Num: 1 Standard Type: INTEGER
|-- --- --- NumValueExpressionNode. Num: 2 Standard Type: INTEGER
|-- --- --- NumValueExpressionNode. Num: 4 Standard Type: INTEGER
*/
