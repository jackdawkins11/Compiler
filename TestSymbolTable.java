

import symbolTable.*;

/* JUnit */

import org.junit.*;
import static org.junit.Assert.*;

public class TestSymbolTable{

	@Test
	public void testTable(){

		SymbolTable symbolTable = new SymbolTable();

		symbolTable.pushScope();

		assertFalse( symbolTable.isProgramName( "jack" ) );

		symbolTable.pushScope();

		symbolTable.addSymbol( EnumId.VARIABLE, EnumVar.REAL, "jack" );

		symbolTable.pushScope();

		assertTrue( symbolTable.isVariableRealName( "jack" ) );

		assertFalse( symbolTable.isVariableRealName( "erik" ) );

		symbolTable.pushScope();

		symbolTable.addSymbol( EnumId.FUNCTION, "erik"  );

		assertTrue( symbolTable.isFunctionName( "erik" ) );

		symbolTable.popScope();

		assertFalse( symbolTable.isFunctionName("erik") );

	}

}


