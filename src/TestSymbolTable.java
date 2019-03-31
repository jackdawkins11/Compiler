
import org.junit.*;
import static org.junit.Assert.*;
import syntaxTree.*;
import symbolTable.SymbolTable;
import scanner.*;
import variableType.*;

public class TestSymbolTable{

	@Test
	public void testOne(){

		SymbolTable symbolTable = new SymbolTable();

		VariableType integerType = new VariableType( 0, 1, EnumStandardType.INTEGER );
		
		VariableType arrayType = new VariableType( 1, 10, EnumStandardType.REAL );

		DeclarationsNode functionVariables = new DeclarationsNode();

		VariableNode functionInteger = new VariableNode( "functionInteger", integerType );
		
		VariableNode functionArray = new VariableNode( "functionArray", arrayType );

		functionVariables.addVariable( functionInteger );
		
		functionVariables.addVariable( functionArray );

		CompoundStatementNode functionBody = new CompoundStatementNode();

		SubProgramNode functionOne = new SubProgramNode( "functionOne",
					EnumStandardType.INTEGER, functionVariables, functionBody ) ;

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
