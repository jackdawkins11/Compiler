

import org.junit.*;
import static org.junit.Assert.*;
import java.util.Scanner;
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

		String expected =
				"ProgramNode. Name: foo\n"
			+	"|-- DeclarationsNode.\n"
			+	"|-- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- VariableNode. Name: fum Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- SubProgramDeclarationsNode.\n"
			+	"|-- CompoundStatementNode.\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 4\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 5\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- OperationExpressionNode. Operation: +\n"
			+	"|-- --- --- --- OperationExpressionNode. Operation: *\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 3\n"
			+	"|-- --- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- IfThenStatementNode.\n"
			+	"|-- --- --- OperationExpressionNode. Operation: <\n"
			+	"|-- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 13\n"
			+	"|-- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 13\n"
			+	"|-- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 26\n"
			+	"|-- --- WriteStatementNode.\n"
			+	"|-- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			;

		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		ProgramNode programNode = null;

		try{

			programNode = parser.program();

		}catch( Exception e ){

			assertFalse( true );

		}
		
		assertEquals( programNode.indentedToString( 0 ), expected );

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
			+	"|-- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- VariableNode. Name: y Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- VariableNode. Name: z Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- CompoundStatementNode.\n"
			+	"|-- --- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- --- VariableNode. Name: a Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- --- OperationExpressionNode. Operation: +\n"
			+	"|-- --- --- --- --- --- OperationExpressionNode. Operation: *\n"
			+	"|-- --- --- --- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- --- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- --- --- --- --- VariableNode. Name: y Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- --- --- --- VariableNode. Name: x Standard Type: REAL Begin Index: 0 End Index: 1\n"
			+	"|-- CompoundStatementNode.\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 4\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 5\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- OperationExpressionNode. Operation: +\n"
			+	"|-- --- --- --- OperationExpressionNode. Operation: *\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 3\n"
			+	"|-- --- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- IfThenStatementNode.\n"
			+	"|-- --- --- OperationExpressionNode. Operation: <\n"
			+	"|-- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 13\n"
			+	"|-- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 13\n"
			+	"|-- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 26\n"
			+	"|-- --- WriteStatementNode.\n"
			+	"|-- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- VariableNode. Name: fo Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- SubProgramValueExpressionNode. Name: funct\n"
			+	"|-- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- --- VariableNode. Name: fee Standard Type: INTEGER Begin Index: 0 End Index: 1\n"	
			+	"|-- --- --- --- NumValueExpressionNode. Num: 3\n"
			+	"|-- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 0\n"
			+	"|-- --- --- --- --- VariableNode. Name: fi Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- SubProgramStatementNode. Name: funct\n"
			;

		MyScanner scanner = new MyScanner( program, false );

		Parser parser = new Parser( scanner );

		ProgramNode programNode = null;

		try{

			programNode = parser.program();

		}catch( Exception e ){

			assertFalse( true );

		}
		
		assertEquals( programNode.indentedToString( 0 ), expected );

	}

}

