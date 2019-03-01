

import symbolTable.*;

/* JUnit */

import org.junit.*;
import static org.junit.Assert.*;

public class TestSymbolTable{

	/*
	 *
	 * Test the symbol table by pushing and popping
	 * the current scope while adding and searching
	 * for symobls.
	 */

	@Test
	public void testTable(){

		SymbolTable symbolTable = new SymbolTable();

		symbolTable.pushScope();

		symbolTable.addSymbol( EnumId.PROGRAM, "prgm" );

		int savedDepth = symbolTable.getDepth();

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
		
		assertFalse( symbolTable.isFunctionName("jack") );

		symbolTable.returnToDepth( savedDepth );

		assertFalse( symbolTable.isVariableRealName( "jack" ) );

		assertTrue( symbolTable.isProgramName( "prgm" ) );


	}

}


