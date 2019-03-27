
import org.junit.*;
import static org.junit.Assert.*;
import syntaxTree.*;
import scanner.*;
import variableType.VariableType;

public class TestSyntaxTree{

	@Test
	public void TestOne(){

		String expected =
				"ProgramNode. Name: JacksProgram\n"
			+	"|-- DeclarationsNode.\n"
			+	"|-- SubProgramDeclarationsNode.\n"
			+	"|-- CompoundStatementNode.\n"
			;

		DeclarationsNode declarationsNode = new DeclarationsNode();

		SubProgramDeclarationsNode subProgramDeclarationsNode = new SubProgramDeclarationsNode();

		CompoundStatementNode compoundStatementNode = new CompoundStatementNode();

		ProgramNode programNode = new ProgramNode( "JacksProgram",
				declarationsNode,
				subProgramDeclarationsNode,
				compoundStatementNode );

		assertEquals( expected, programNode.indentedToString( 0 ) );

	}

	@Test
	public void TestTwo(){

		String expected =
				"ProgramNode. Name: JacksProgram\n"
			+	"|-- DeclarationsNode.\n"
			+	"|-- --- StandardVariableNode. Name: jacksInteger\n"
			+	"|-- --- ArrayVariableNode. Name: jacksArray Rows: 1 Cols: 10\n"
			+	"|-- SubProgramDeclarationsNode.\n"
			+	"|-- --- FunctionNode. Name: functionOne\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- --- StandardVariableNode. Name: functionInteger\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- --- ArrayVariableNode. Name: functionArray Rows: 1 Cols: 10\n"
			+	"|-- --- --- CompoundStatementNode.\n"
			+	"|-- CompoundStatementNode.\n"
			;

		VariableType integerType = new VariableType( EnumToken.INTEGER );
		
		VariableType arrayType = new VariableType( 1, 10, EnumToken.REAL );

		DeclarationsNode programVariables = new DeclarationsNode();
		
		programVariables.addVariable( new StandardVariableNode( "jacksInteger", integerType ) );
		
		programVariables.addVariable( new ArrayVariableNode( "jacksArray", arrayType ) );

		SubProgramDeclarationsNode subProgramDeclarationsNode = new SubProgramDeclarationsNode();

		DeclarationsNode functionParameters = new DeclarationsNode();

		DeclarationsNode functionVariables = new DeclarationsNode();

		functionParameters.addVariable( new StandardVariableNode( "functionInteger", integerType ) );

		functionVariables.addVariable( new ArrayVariableNode( "functionArray", arrayType ) );

		CompoundStatementNode functionBody = new CompoundStatementNode();

		subProgramDeclarationsNode.addSubProgram( new FunctionNode( "functionOne", functionParameters,
					integerType, functionVariables, functionBody ) );

		CompoundStatementNode main = new CompoundStatementNode();

		ProgramNode programNode = new ProgramNode( "JacksProgram",
				programVariables,
				subProgramDeclarationsNode,
				main );


		assertEquals( expected, programNode.indentedToString( 0 ) );
	}
	
	@Test
	public void TestThree(){

		String expected =
				"ProgramNode. Name: JacksProgram\n"
			+	"|-- DeclarationsNode.\n"
			+	"|-- --- StandardVariableNode. Name: jacksInteger\n"
			+	"|-- --- ArrayVariableNode. Name: jacksArray Rows: 1 Cols: 10\n"
			+	"|-- SubProgramDeclarationsNode.\n"
			+	"|-- --- FunctionNode. Name: functionOne\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- --- StandardVariableNode. Name: functionInteger\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- --- ArrayVariableNode. Name: functionArray Rows: 1 Cols: 10\n"
			+	"|-- --- --- CompoundStatementNode.\n"
			+	"|-- --- --- --- ArrayAssignmentStatementNode.\n"
			+	"|-- --- --- --- --- ArrayVariableNode. Name: functionArray Rows: 1 Cols: 10\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 1000\n"
			+	"|-- --- --- --- --- VariableValueExpressionNode.\n"
			+	"|-- --- --- --- --- --- StandardVariableNode. Name: functionInteger\n"
			+	"|-- CompoundStatementNode.\n"
			;

		VariableType integerType = new VariableType( EnumToken.INTEGER );
		
		VariableType arrayType = new VariableType( 1, 10, EnumToken.REAL );

		DeclarationsNode programVariables = new DeclarationsNode();
		
		programVariables.addVariable( new StandardVariableNode( "jacksInteger", integerType ) );
		
		programVariables.addVariable( new ArrayVariableNode( "jacksArray", arrayType ) );

		SubProgramDeclarationsNode subProgramDeclarationsNode = new SubProgramDeclarationsNode();

		DeclarationsNode functionParameters = new DeclarationsNode();

		DeclarationsNode functionVariables = new DeclarationsNode();

		StandardVariableNode functionInteger = new StandardVariableNode( "functionInteger", integerType );
		
		ArrayVariableNode functionArray = new ArrayVariableNode( "functionArray", arrayType );

		functionParameters.addVariable( functionInteger );

		functionVariables.addVariable( functionArray );

		CompoundStatementNode functionBody = new CompoundStatementNode();

		NumValueExpressionNode arrayOffset = new NumValueExpressionNode( "1000" );

		VariableValueExpressionNode rValue = new VariableValueExpressionNode( functionInteger );

		functionBody.addStatement( new ArrayAssignmentStatementNode( functionArray, arrayOffset, rValue ) );	

		subProgramDeclarationsNode.addSubProgram( new FunctionNode( "functionOne", functionParameters,
					integerType, functionVariables, functionBody ) );

		CompoundStatementNode main = new CompoundStatementNode();

		ProgramNode programNode = new ProgramNode( "JacksProgram",
				programVariables,
				subProgramDeclarationsNode,
				main );

		assertEquals( expected, programNode.indentedToString( 0 ) );
	}



}

