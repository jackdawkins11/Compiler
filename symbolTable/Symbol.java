package symbolTable;

/*
 *
 * A symbol can be a few different types,
 * and if it is a variable
 * it can be a few different types
 * of variables. All this is stored
 * in Symbol.
 */

public class Symbol{

	EnumId idType;

	EnumVar varType;

	int rows, cols;

	String lexeme;

	/*
	 *
	 * There are three constructors and also
	 * three functions called init that
	 * function similarly by setting private values.
	 * There are 3, one for each of
	 * -Variable
	 * -arrays
	 * -functions or programs
	 */

	//this is the general one when all values are specified
	//or it is an array

	public Symbol( EnumId idType_tmp, EnumVar varType_tmp, int rows_tmp, int cols_tmp, String lexeme_tmp ){
		
		idType = idType_tmp;

		varType = varType_tmp;

		rows = rows_tmp;

		cols = cols_tmp;

		lexeme = lexeme_tmp;

	}

	//this is the one for variables
	
	public Symbol( EnumId idType_tmp, EnumVar varType_tmp, String lexeme_tmp ){
		
		assert( idType_tmp == EnumId.VARIABLE 
				&& varType_tmp != EnumVar.NULL );

		idType = idType_tmp;

		varType = varType_tmp;

		rows = -1;

		cols = -1;

		lexeme = lexeme_tmp;

	}

	//this is the one for functions or programs
	
	public Symbol( EnumId idType_tmp, String lexeme_tmp ){
		
		assert( idType_tmp != EnumId.ARRAY
				&& idType_tmp != EnumId.VARIABLE );

		idType = idType_tmp;

		varType = EnumVar.NULL;

		rows = -1;

		cols = -1;

		lexeme = lexeme_tmp;

	}

	//arrays or everything known

	public void init( EnumId idType_tmp, EnumVar varType_tmp, int rows_tmp, int cols_tmp, String lexeme_tmp ){
		
		idType = idType_tmp;

		varType = varType_tmp;

		rows = rows_tmp;

		cols = cols_tmp;

		lexeme = lexeme_tmp;

	}

	//variables
	
	public void init( EnumId idType_tmp, EnumVar varType_tmp, String lexeme_tmp ){
		
		assert( idType_tmp == EnumId.VARIABLE 
				&& varType_tmp != EnumVar.NULL );

		idType = idType_tmp;

		varType = varType_tmp;

		rows = -1;

		cols = -1;

		lexeme = lexeme_tmp;

	}

	//program or function
	
	public void init( EnumId idType_tmp, String lexeme_tmp ){
		
		assert( idType_tmp != EnumId.ARRAY
				&& idType_tmp != EnumId.VARIABLE );

		idType = idType_tmp;

		varType = EnumVar.NULL;

		rows = -1;

		cols = -1;

		lexeme = lexeme_tmp;

	}

	/*
	 *
	 * getters
	 *
	 */

	public EnumId getIdType(){
		return idType;
	}

	public EnumVar getVarType(){
		return varType;
	}

	public int getRows(){
		return rows;
	}

	public int getCols(){
		return cols;
	}

	public String getIdentifier(){
		return lexeme;
	}

};

