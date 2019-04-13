
import org.junit.*;
import static org.junit.Assert.*;
import syntaxTree.*;
import scanner.*;
import variableType.*;
import java.util.ArrayList;

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
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- CompoundStatementNode.\n"
			+	"|-- CompoundStatementNode.\n"
			;

		DeclarationsNode programVariables = new DeclarationsNode();
		
		programVariables.addVariable( new VariableNode( "jacksInteger", new VariableType( 0, 1, EnumStandardType.INTEGER ) ) );
		
		programVariables.addVariable( new VariableNode( "jacksArray", new VariableType( 1, 10, EnumStandardType.INTEGER ) ) );

		SubProgramDeclarationsNode subProgramDeclarationsNode = new SubProgramDeclarationsNode();

		DeclarationsNode functionVariables = new DeclarationsNode();
		
		DeclarationsNode functionArguments = new DeclarationsNode();
	
		functionVariables.addVariable( new VariableNode( "functionInteger", new VariableType( 0, 1, EnumStandardType.INTEGER ) ) );
		
		functionVariables.addVariable( new VariableNode( "functionArray", new VariableType( 1, 10, EnumStandardType.INTEGER ) ) );

		CompoundStatementNode functionBody = new CompoundStatementNode();

		subProgramDeclarationsNode.addSubProgram( new SubProgramNode( "functionOne", EnumStandardType.INTEGER,
					functionVariables, functionArguments, functionBody ) );

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
			+	"|-- --- --- DeclarationsNode.\n"
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
		
		DeclarationsNode functionArguments = new DeclarationsNode();

		VariableNode functionInteger = new VariableNode( "functionInteger", integerType );
		
		VariableNode functionArray = new VariableNode( "functionArray", arrayType );

		functionVariables.addVariable( functionInteger );

		functionVariables.addVariable( functionArray );

		CompoundStatementNode functionBody = new CompoundStatementNode();

		NumValueExpressionNode arrayOffset = new NumValueExpressionNode( "1" );

		VariableValueExpressionNode rValue = new VariableValueExpressionNode( functionInteger, arrayOffset );

		functionBody.addStatement( new VariableAssignmentStatementNode( functionArray, arrayOffset, rValue ) );	

		subProgramDeclarationsNode.addSubProgram( new SubProgramNode( "functionOne", EnumStandardType.INTEGER,
					functionVariables, functionArguments, functionBody ) );

		CompoundStatementNode main = new CompoundStatementNode();

		ProgramNode programNode = new ProgramNode( "JacksProgram",
				programVariables,
				subProgramDeclarationsNode,
				main );

		assertEquals( expected, programNode.indentedToString( 0 ) );
	
	}


	@Test
	public void TestFour(){
		
		String expected =
				"ProgramNode. Name: JacksProgram\n"
			+	"|-- DeclarationsNode.\n"
			+	"|-- --- VariableNode. Name: jacksInteger Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- VariableNode. Name: jacksArray Standard Type: REAL Begin Index: 1 End Index: 10\n"
			+	"|-- SubProgramDeclarationsNode.\n"
			+	"|-- --- SubProgramNode. Name: functionOne\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- --- VariableNode. Name: functionInteger Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- --- --- VariableNode. Name: functionArray Standard Type: INTEGER Begin Index: 1 End Index: 10\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- CompoundStatementNode.\n"
			+	"|-- --- --- --- VariableAssignmentStatementNode.\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 8 Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- VariableNode. Name: functionArray Standard Type: INTEGER Begin Index: 1 End Index: 10\n"
			+	"|-- --- --- --- --- VariableValueExpressionNode. Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- --- NumValueExpressionNode. Num: 1 Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- --- VariableNode. Name: functionInteger Standard Type: INTEGER Begin Index: 0 End Index: 1\n"
			+	"|-- --- SubProgramNode. Name: functionTwo\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- DeclarationsNode.\n"
			+	"|-- --- --- CompoundStatementNode.\n"
			+	"|-- --- --- --- ReturnStatementNode.\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 33.3 Standard Type: REAL\n"
			+	"|-- CompoundStatementNode.\n"
			+	"|-- --- WhileDoStatementNode.\n"
			+	"|-- --- --- OperationExpressionNode. Operation: < Standard Type: REAL\n"
			+	"|-- --- --- --- NotValueExpressionNode. Standard Type: REAL\n"
		       	+	"|-- --- --- --- --- VariableValueExpressionNode. Standard Type: REAL\n"
			+	"|-- --- --- --- --- --- NumValueExpressionNode. Num: 3 Standard Type: INTEGER\n"
			+	"|-- --- --- --- --- --- VariableNode. Name: jacksArray Standard Type: REAL Begin Index: 1 End Index: 10\n"
			+	"|-- --- --- --- SubProgramValueExpressionNode. Name: functionTwo Standard Type: REAL\n"
			+	"|-- --- --- --- --- NumValueExpressionNode. Num: 4 Standard Type: INTEGER\n"
			+	"|-- --- --- WriteStatementNode.\n"
			+	"|-- --- --- --- NumValueExpressionNode. Num: 3.14 Standard Type: REAL\n"
			;

		//variable types

		VariableType integerType = new VariableType( 0, 1, EnumStandardType.INTEGER);
		
		VariableType arrayIntegerType = new VariableType( 1, 10, EnumStandardType.INTEGER );
		
		VariableType arrayRealType = new VariableType( 1, 10, EnumStandardType.REAL );

		//programVariables

		DeclarationsNode programVariables = new DeclarationsNode();
		
		VariableNode jacksInteger = new VariableNode( "jacksInteger", integerType );

		VariableNode jacksArray = new VariableNode( "jacksArray", arrayRealType );

		programVariables.addVariable( jacksInteger );

		programVariables.addVariable( jacksArray );

		//functionOne

		//functionOneVariables

		DeclarationsNode functionOneVariables = new DeclarationsNode();
		
		DeclarationsNode functionOneArguments = new DeclarationsNode();

		VariableNode functionInteger = new VariableNode( "functionInteger", integerType );
		
		VariableNode functionArray = new VariableNode( "functionArray", arrayIntegerType );

		functionOneVariables.addVariable( functionInteger );

		functionOneVariables.addVariable( functionArray );

		//functionOne statement

		NumValueExpressionNode rValueIndex = new NumValueExpressionNode( "1" );
		
		VariableValueExpressionNode rValue = new VariableValueExpressionNode( functionInteger, rValueIndex );
		
		NumValueExpressionNode assignmentIndex = new NumValueExpressionNode( "8" );
		
		VariableAssignmentStatementNode variableAssignmentStatementNode1 =
		       	new VariableAssignmentStatementNode( functionArray, assignmentIndex, rValue );

		CompoundStatementNode functionOneBody = new CompoundStatementNode();
		
		functionOneBody.addStatement( variableAssignmentStatementNode1 );
		
		//create function

		SubProgramNode functionOne = new SubProgramNode( "functionOne", EnumStandardType.INTEGER,
					functionOneVariables, functionOneArguments, functionOneBody );
	
		//functionTwo

		//functionTwoVariables

		DeclarationsNode functionTwoVariables = new DeclarationsNode();
		
		DeclarationsNode functionTwoArguments = new DeclarationsNode();

		//functionTwo statement
		
		ReturnStatementNode returnStatementNode = new ReturnStatementNode( new NumValueExpressionNode( "33.3" ) );
		
		CompoundStatementNode functionTwoBody = new CompoundStatementNode();
		
		functionTwoBody.addStatement( returnStatementNode );
		
		//create function

		SubProgramNode functionTwo = new SubProgramNode( "functionTwo", EnumStandardType.REAL,
					functionTwoVariables, functionTwoArguments, functionTwoBody );
	
		//add the functions

		SubProgramDeclarationsNode subProgramDeclarationsNode = new SubProgramDeclarationsNode();
		
		subProgramDeclarationsNode.addSubProgram( functionOne );
		
		subProgramDeclarationsNode.addSubProgram( functionTwo );

		//program body

		NumValueExpressionNode varIndex = new NumValueExpressionNode( "3" );
		
		VariableValueExpressionNode varExpr = new VariableValueExpressionNode( jacksArray, varIndex );
		
		NotValueExpressionNode notValueExpressionNode = new NotValueExpressionNode( varExpr );

		NumValueExpressionNode functionParam = new NumValueExpressionNode( "4" );

		ArrayList< ExpressionNode > functionParams = new ArrayList< ExpressionNode >();

		functionParams.add( functionParam );

		SubProgramValueExpressionNode subProgramValueExpressionNode = new SubProgramValueExpressionNode( functionTwo, functionParams );

		OperationExpressionNode operationExpressionNode = new OperationExpressionNode( notValueExpressionNode, "<", subProgramValueExpressionNode ); 

		WriteStatementNode writeStatementNode = new WriteStatementNode( new NumValueExpressionNode( "3.14" ) );

		CompoundStatementNode main = new CompoundStatementNode();

		main.addStatement( new WhileDoStatementNode( operationExpressionNode, writeStatementNode ) );

		ProgramNode programNode = new ProgramNode( "JacksProgram",
				programVariables,
				subProgramDeclarationsNode,
				main );

		assertEquals( expected, programNode.indentedToString( 0 ) );
	
	}


}

