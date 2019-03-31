
import org.junit.*;
import static org.junit.Assert.*;
import syntaxTree.*;
import scanner.*;
import variableType.*;

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
			+	"|-- --- VariableNode. Name: jacksInteger Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- VariableNode. Name: jacksArray Standard Type: INTEGER Begin Index: 1 End Index: 10\n"
			+	"|-- SubProgramDeclarationsNode.\n"
			+	"|-- --- SubProgramNode. Name: functionOne\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- --- VariableNode. Name: functionInteger Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- VariableNode. Name: functionArray Standard Type: INTEGER Begin Index: 1 End Index: 10\n"
			+	"|-- --- --- CompoundStatementNode.\n"
			+	"|-- CompoundStatementNode.\n"
			;

		DeclarationsNode programVariables = new DeclarationsNode();
		
		programVariables.addVariable( new VariableNode( "jacksInteger", new VariableType( 0, 1, EnumStandardType.INTEGER ) ) );
		
		programVariables.addVariable( new VariableNode( "jacksArray", new VariableType( 1, 10, EnumStandardType.INTEGER ) ) );

		SubProgramDeclarationsNode subProgramDeclarationsNode = new SubProgramDeclarationsNode();

		DeclarationsNode functionVariables = new DeclarationsNode();
	
		functionVariables.addVariable( new VariableNode( "functionInteger", new VariableType( 0, 1, EnumStandardType.INTEGER ) ) );
		
		functionVariables.addVariable( new VariableNode( "functionArray", new VariableType( 1, 10, EnumStandardType.INTEGER ) ) );

		CompoundStatementNode functionBody = new CompoundStatementNode();

		subProgramDeclarationsNode.addSubProgram( new SubProgramNode( "functionOne", EnumStandardType.INTEGER,
					functionVariables, functionBody ) );

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
			+	"|-- --- VariableNode. Name: jacksInteger Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- VariableNode. Name: jacksArray Standard Type: INTEGER Begin Index: 1 End Index: 10\n"
			+	"|-- SubProgramDeclarationsNode.\n"
			+	"|-- --- SubProgramNode. Name: functionOne\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- --- VariableNode. Name: functionInteger Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- VariableNode. Name: functionArray Standard Type: INTEGER Begin Index: 1 End Index: 10\n"
			+	"|-- --- --- CompoundStatementNode.\n"
			+	"|-- --- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 1 Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- VariableNode. Name: functionArray Standard Type: INTEGER Begin Index: 1 End Index: 10\n"
			+	"|-- --- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- --- NumValueExpressionNode. Num: 1 Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- --- VariableNode. Name: functionInteger Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- CompoundStatementNode.\n"
			;

		VariableType integerType = new VariableType( 0, 1, EnumStandardType.INTEGER );
		
		VariableType arrayType = new VariableType( 1, 10, EnumStandardType.INTEGER );

		DeclarationsNode programVariables = new DeclarationsNode();
		
		programVariables.addVariable( new VariableNode( "jacksInteger", integerType ) );
		
		programVariables.addVariable( new VariableNode( "jacksArray", arrayType ) );

		SubProgramDeclarationsNode subProgramDeclarationsNode = new SubProgramDeclarationsNode();

		DeclarationsNode functionVariables = new DeclarationsNode();

		VariableNode functionInteger = new VariableNode( "functionInteger", integerType );
		
		VariableNode functionArray = new VariableNode( "functionArray", arrayType );

		functionVariables.addVariable( functionInteger );

		functionVariables.addVariable( functionArray );

		CompoundStatementNode functionBody = new CompoundStatementNode();

		NumValueExpressionNode arrayOffset = new NumValueExpressionNode( "1" );

		VariableValueExpressionNode rValue = new VariableValueExpressionNode( functionInteger, arrayOffset );

		functionBody.addStatement( new VariableAssignmentStatementNode( functionArray, arrayOffset, rValue ) );	

		subProgramDeclarationsNode.addSubProgram( new SubProgramNode( "functionOne", EnumStandardType.INTEGER,
					functionVariables, functionBody ) );

		CompoundStatementNode main = new CompoundStatementNode();

		ProgramNode programNode = new ProgramNode( "JacksProgram",
				programVariables,
				subProgramDeclarationsNode,
				main );

		assertEquals( expected, programNode.indentedToString( 0 ) );
	
	}


}

