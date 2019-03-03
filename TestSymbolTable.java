

import symbolTable.*;

/* JUnit */

import org.junit.*;
import static org.junit.Assert.*;

public class TestSymbolTable{

	/*
	 *
	 * Test the symbol table by pushing and popping
	 * the current scope while adding, searching and deleting
	 * symobls.
	 */

	@Test
	public void testTable(){

		SymbolTable symbolTable = new SymbolTable();

		symbolTable.pushScope();

		symbolTable.add( new Symbol( EnumId.PROGRAM, "prgm" ) );

		assertTrue( symbolTable.exists( EnumId.PROGRAM, "prgm" ) );
		assertFalse( symbolTable.exists( EnumId.FUNCTION, "prgm" ) );
		
		int savedDepth = symbolTable.getDepth();
		
		symbolTable.pushScope();

		symbolTable.add( new Symbol( EnumId.VARIABLE, EnumVar.REAL, "jack" )  );
		
		assertTrue( symbolTable.exists( EnumId.PROGRAM, "prgm" ) );
		assertTrue( symbolTable.exists( EnumId.VARIABLE, "jack" ) );
		assertFalse( symbolTable.exists( EnumId.VARIABLE, "gjack" ) );
		
		symbolTable.pushScope();

		symbolTable.add( new Symbol( EnumId.ARRAY, EnumVar.INTEGER, 1, 4, "arr" ) );
	
		assertTrue( symbolTable.exists( EnumId.PROGRAM, "prgm" ) );
		assertTrue( symbolTable.exists( EnumId.VARIABLE, "jack" ) );
		assertTrue( symbolTable.exists( EnumId.ARRAY, "arr" ) );

		symbolTable.delete( EnumId.VARIABLE, "jack" );

		assertTrue( symbolTable.exists( EnumId.PROGRAM, "prgm" ) );
		assertFalse( symbolTable.exists( EnumId.VARIABLE, "jack" ) );
		assertTrue( symbolTable.exists( EnumId.ARRAY, "arr" ) );

		symbolTable.popScope();
		
		assertTrue( symbolTable.exists( EnumId.PROGRAM, "prgm" ) );
		assertFalse( symbolTable.exists( EnumId.ARRAY, "arr" ) );

		symbolTable.returnToDepth( savedDepth );

		assertTrue( symbolTable.exists( EnumId.PROGRAM, "prgm" ) );
	}

}


