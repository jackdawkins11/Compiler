

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
			+	"|-- --- StandardVariableNode. Name: fee\n"
			+	"|-- --- StandardVariableNode. Name: fi\n"
			+	"|-- --- StandardVariableNode. Name: fo\n"
			+	"|-- --- StandardVariableNode. Name: fum\n"
			+	"|-- SubProgramDeclarationsNode.\n"
			+	"|-- CompoundStatementNode.\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- StandardVariableNode. Name: fee\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 4\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- StandardVariableNode. Name: fi\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 5\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- StandardVariableNode. Name: fo\n"
			+	"|-- --- --- OperationExpressionNode. Operation: +\n"
			+	"|-- --- --- --- OperationExpressionNode. Operation: *\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 3\n"
			+	"|-- --- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- --- StandardVariableNode. Name: fee\n"
			+	"|-- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- StandardVariableNode. Name: fi\n"
			+	"|-- --- IfThenStatementNode.\n"
			+	"|-- --- --- OperationExpressionNode. Operation: <\n"
			+	"|-- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- StandardVariableNode. Name: fo\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 13\n"
			+	"|-- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- StandardVariableNode. Name: fo\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 13\n"
			+	"|-- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- StandardVariableNode. Name: fo\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 26\n"
			+	"|-- --- WriteStatementNode.\n"
			+	"|-- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- StandardVariableNode. Name: fo\n"
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
			+	"|-- --- StandardVariableNode. Name: fee\n"
			+	"|-- --- StandardVariableNode. Name: fi\n"
			+	"|-- --- StandardVariableNode. Name: fo\n"
			+	"|-- --- StandardVariableNode. Name: fum\n"
			+	"|-- SubProgramDeclarationsNode.\n"
			+	"|-- --- FunctionNode. Name: funct\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- --- StandardVariableNode. Name: x\n"
			+	"|-- --- --- --- StandardVariableNode. Name: y\n"
			+	"|-- --- --- --- StandardVariableNode. Name: z\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- --- StandardVariableNode. Name: a\n"
			+	"|-- --- --- --- StandardVariableNode. Name: b\n"
			+	"|-- --- --- --- StandardVariableNode. Name: c\n"
			+	"|-- --- --- CompoundStatementNode.\n"
			+	"|-- --- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- --- StandardVariableNode. Name: a\n"
			+	"|-- --- --- --- --- OperationExpressionNode. Operation: +\n"
			+	"|-- --- --- --- --- --- OperationExpressionNode. Operation: *\n"
			+	"|-- --- --- --- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- --- --- --- StandardVariableNode. Name: x\n"
			+	"|-- --- --- --- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- --- --- --- StandardVariableNode. Name: y\n"
			+	"|-- --- --- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- --- --- StandardVariableNode. Name: x\n"
			+	"|-- CompoundStatementNode.\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- StandardVariableNode. Name: fee\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 4\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- StandardVariableNode. Name: fi\n"
			+	"|-- --- --- NumValueExpressionNode. Num: 5\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- StandardVariableNode. Name: fo\n"
			+	"|-- --- --- OperationExpressionNode. Operation: +\n"
			+	"|-- --- --- --- OperationExpressionNode. Operation: *\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 3\n"
			+	"|-- --- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- --- StandardVariableNode. Name: fee\n"
			+	"|-- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- StandardVariableNode. Name: fi\n"
			+	"|-- --- IfThenStatementNode.\n"
			+	"|-- --- --- OperationExpressionNode. Operation: <\n"
			+	"|-- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- StandardVariableNode. Name: fo\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 13\n"
			+	"|-- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- StandardVariableNode. Name: fo\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 13\n"
			+	"|-- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- StandardVariableNode. Name: fo\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 26\n"
			+	"|-- --- WriteStatementNode.\n"
			+	"|-- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- StandardVariableNode. Name: fo\n"
			+	"|-- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- StandardVariableNode. Name: fee\n"
			+	"|-- --- --- FunctionValueExpressionNode. Name: funct\n"
			+	"|-- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- StandardVariableNode. Name: fee\n"	
			+	"|-- --- --- --- NumValueExpressionNode. Num: 3\n"
			+	"|-- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- StandardVariableNode. Name: fi\n"
			+	"|-- --- ProcedureStatementNode. Name: funct\n"
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

