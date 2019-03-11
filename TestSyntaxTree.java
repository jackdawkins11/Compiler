
import org.junit.*;
import static org.junit.Assert.*;
import syntaxTree.*;
import scanner.*;

public class TestSyntaxTree{
	
	@Test
	public void test(){

		ProgramNode programNode1 = new ProgramNode( "sample" );

		DeclarationsNode declarationsNode1 = new DeclarationsNode();

		declarationsNode1.addVariable( new VariableNode( "dollars" ) );
		
		declarationsNode1.addVariable( new VariableNode( "yen" ) );
		
		declarationsNode1.addVariable( new VariableNode( "bitcoins" ) );

		programNode1.setVariables( declarationsNode1 );

		SubProgramDeclarationsNode subProgramDeclarationsNode1 = new SubProgramDeclarationsNode();

		programNode1.setFunctions( subProgramDeclarationsNode1 );

		CompoundStatementNode compoundStatementNode1 = new CompoundStatementNode();

		AssignmentStatementNode assignmentStatementNode1 = new AssignmentStatementNode();

		assignmentStatementNode1.setLValue( new VariableNode( "dollars" ) );

		assignmentStatementNode1.setExpression( new ValueNode( "1000000" ) );

		compoundStatementNode1.addStatement( assignmentStatementNode1 );

		AssignmentStatementNode assignmentStatementNode2 = new AssignmentStatementNode();

		assignmentStatementNode2.setLValue( new VariableNode( "yen" ) );

		OperationNode operationNode1 = new OperationNode( EnumToken.MULOP );

		operationNode1.setLeft( new VariableNode( "dollars" ) );

		operationNode1.setRight( new ValueNode( "110" ) );

		assignmentStatementNode2.setExpression( operationNode1 );

		compoundStatementNode1.addStatement( assignmentStatementNode2 );

		AssignmentStatementNode assignmentStatementNode3 = new AssignmentStatementNode();

		assignmentStatementNode3.setLValue( new VariableNode( "bitcoins" ) );

		OperationNode operationNode2 = new OperationNode( EnumToken.MULOP );

		operationNode2.setLeft( new VariableNode( "dollars" ) );

		operationNode2.setRight( new ValueNode( "3900" ) );

		assignmentStatementNode3.setExpression( operationNode2 );

		compoundStatementNode1.addStatement( assignmentStatementNode3 );

		programNode1.setMain( compoundStatementNode1 );

		String expectedString = 
			  "Program: sample\n"
			+ "|-- Declarations\n"
			+ "|-- --- Name: dollars\n"
			+ "|-- --- Name: yen\n"
			+ "|-- --- Name: bitcoins\n"
			+ "|-- SubProgramDeclarations\n"
			+ "|-- Compound Statement\n"
			+ "|-- --- Assignment\n"
			+ "|-- --- --- Name: dollars\n"
			+ "|-- --- --- Value: 1000000\n"
			+ "|-- --- Assignment\n"
			+ "|-- --- --- Name: yen\n"
			+ "|-- --- --- Operation: MULOP\n"
			+ "|-- --- --- --- Name: dollars\n"
			+ "|-- --- --- --- Value: 110\n"
			+ "|-- --- Assignment\n"
			+ "|-- --- --- Name: bitcoins\n"
			+ "|-- --- --- Operation: MULOP\n"
			+ "|-- --- --- --- Name: dollars\n"
			+ "|-- --- --- --- Value: 3900\n"   ;

		assertEquals( programNode1.indentedToString( 0 ), expectedString );

	}

}
