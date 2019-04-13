
import org.junit.*;
import static org.junit.Assert.*;
import syntaxTree.*;
import scanner.*;
import variableType.*;
import java.util.ArrayList;

public class TestCodeGen{

	public static void TestOne(){

		DeclarationsNode declarationsNode = new DeclarationsNode();

		SubProgramDeclarationsNode subProgramDeclarationsNode = new SubProgramDeclarationsNode();

		CompoundStatementNode compoundStatementNode = new CompoundStatementNode();

		NumValueExpressionNode lvalue = new NumValueExpressionNode( "13" );
		
		NumValueExpressionNode rvalue = new NumValueExpressionNode( "14" );

		OperationExpressionNode operationExpressionNode = null;

		try{

			operationExpressionNode = new OperationExpressionNode( lvalue, "+", rvalue );

		}catch( Exception e ){
			System.out.println( "Error .." );
			System.exit( 1 );
		}

		WriteStatementNode writeStatementNode = new WriteStatementNode( operationExpressionNode );

		compoundStatementNode.addStatement( writeStatementNode );

		ProgramNode programNode = new ProgramNode( "ProgramOne",
				declarationsNode,
				subProgramDeclarationsNode,
				compoundStatementNode );

		System.out.println( programNode.toMips() );

	}

	public static void TestTwo(){

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

		ProgramNode programNode = new ProgramNode( "ProgramTwo",
				programVariables,
				subProgramDeclarationsNode,
				main );

		System.out.println( programNode.toMips() );
	
	}

	public static void TestThree(){
				
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

		ProgramNode programNode = new ProgramNode( "ProgramThree",
				programVariables,
				subProgramDeclarationsNode,
				main );

		System.out.println( programNode.toMips() );
	}


	public static void TestFour(){
			
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
		
		ReturnStatementNode returnStatementNode = new ReturnStatementNode( new NumValueExpressionNode( "33" ) );
		
		CompoundStatementNode functionTwoBody = new CompoundStatementNode();
		
		functionTwoBody.addStatement( returnStatementNode );
		
		//create function

		SubProgramNode functionTwo = new SubProgramNode( "functionTwo", EnumStandardType.INTEGER,
					functionTwoVariables, functionTwoVariables, functionTwoBody );
	
		//add the functions

		SubProgramDeclarationsNode subProgramDeclarationsNode = new SubProgramDeclarationsNode();
		
		subProgramDeclarationsNode.addSubProgram( functionOne );
		
		subProgramDeclarationsNode.addSubProgram( functionTwo );

		//program body

		NumValueExpressionNode varIndex = new NumValueExpressionNode( "0" );
	
		VariableValueExpressionNode varExpr = new VariableValueExpressionNode( jacksInteger, varIndex );

		NotValueExpressionNode notValueExpressionNode = null;

		try{

			notValueExpressionNode = new NotValueExpressionNode( varExpr );

		}catch( Exception e ){
			System.out.println( "failed to create NotValueExpressionNode" );
			System.exit( 1 );
		}

		NumValueExpressionNode functionParam = new NumValueExpressionNode( "4" );

		ArrayList< ExpressionNode > functionParams = new ArrayList< ExpressionNode >();

		functionParams.add( functionParam );

		SubProgramValueExpressionNode subProgramValueExpressionNode = new SubProgramValueExpressionNode( functionTwo, functionParams );
		
		OperationExpressionNode operationExpressionNode = null;
		
		try{

			operationExpressionNode = new OperationExpressionNode( notValueExpressionNode, "<", subProgramValueExpressionNode ); 

		}catch( Exception e ){
			System.out.println( e.getMessage() );
			System.exit( 1 );
		}

		WriteStatementNode writeStatementNode = new WriteStatementNode( new NumValueExpressionNode( "3.14" ) );

		CompoundStatementNode main = new CompoundStatementNode();

		main.addStatement( new WhileDoStatementNode( operationExpressionNode, writeStatementNode ) );

		ProgramNode programNode = new ProgramNode( "ProgramFour",
				programVariables,
				subProgramDeclarationsNode,
				main );

		System.out.println( programNode.toMips() );
	
	}

	public static void main( String args[] ){

		TestOne();

		TestTwo();

		TestThree();

		TestFour();

	}

}

