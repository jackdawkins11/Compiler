
import org.junit.*;
import static org.junit.Assert.*;
import syntaxTree.*;
import symbolTable.SymbolTable;
import scanner.*;
import variableType.VariableType;

public class TestSymbolTable{

	@Test
	public void testOne(){

		SymbolTable symbolTable = new SymbolTable();

		VariableType integerType = new VariableType( EnumToken.INTEGER );
		
		VariableType arrayType = new VariableType( 1, 10, EnumToken.REAL );

		DeclarationsNode functionParameters = new DeclarationsNode();

		DeclarationsNode functionVariables = new DeclarationsNode();

		StandardVariableNode functionInteger = new StandardVariableNode( "functionInteger", integerType );
		
		ArrayVariableNode functionArray = new ArrayVariableNode( "functionArray", arrayType );

		functionParameters.addVariable( functionInteger );

		functionVariables.addVariable( functionArray );

		CompoundStatementNode functionBody = new CompoundStatementNode();

		FunctionNode functionOne = new FunctionNode( "functionOne", functionParameters,
					integerType, functionVariables, functionBody ) ;

		symbolTable.addSubProgram( functionOne );

		symbolTable.addVariable( functionArray );

		assertTrue( symbolTable.isSubProgramName( "functionOne" ) );

		assertTrue( symbolTable.isVariableName( "functionArray" ) );

		assertTrue( functionOne == symbolTable.getSubProgramByName( "functionOne" ) );
		
		assertTrue( functionArray == symbolTable.getVariableByName( "functionArray" ) );

		assertFalse( symbolTable.isSubProgramName( "functionArray" ) );

		assertFalse( symbolTable.isVariableName( "functionOne" ) );

	}

}
