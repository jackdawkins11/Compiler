
import symbolTable.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TestSymbolTable{

	@Test
	public void testTable(){

		SymbolTable symbolTable  = new SymbolTable();

		Symbol programSymbol = new Symbol( EnumId.PROGRAM, "main" );

		symbolTable.add( programSymbol );

		assertTrue( symbolTable.exists( EnumId.PROGRAM, "main" ) );

		Symbol funct1Symbol = new Symbol( EnumId.FUNCTION, "addNum" );

		symbolTable.add( funct1Symbol );

		assertTrue( symbolTable.exists( EnumId.FUNCTION, "addNum" ) );

		Symbol var1Symbol = new Symbol( EnumId.VARIABLE, EnumVar.INTEGER, "count" );

		symbolTable.add( var1Symbol );

		assertTrue( symbolTable.exists( EnumId.VARIABLE, "count" ) );

		Symbol var2Symbol = new Symbol( EnumId.ARRAY, EnumVar.REAL, 10, 1, "accumArray" );

		symbolTable.add( var2Symbol );

		assertTrue( symbolTable.exists( EnumId.ARRAY, "accumArray" ) );

		symbolTable.delete( EnumId.PROGRAM, "main" );
		
		assertFalse( symbolTable.exists( EnumId.PROGRAM, "main" ) );
	
		symbolTable.delete( EnumId.FUNCTION, "addNum" ) ;
		
		assertFalse( symbolTable.exists( EnumId.FUNCTION, "addNum" ) );
		
		symbolTable.delete( EnumId.VARIABLE, "count" ) ;
		
		assertFalse( symbolTable.exists( EnumId.VARIABLE, "count" ) );
		
		symbolTable.delete( EnumId.ARRAY, "accumArray" );
		
		assertFalse( symbolTable.exists( EnumId.ARRAY, "accumArray" ) );

	}

}
