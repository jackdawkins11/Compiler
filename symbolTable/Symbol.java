
package symbolTable;

public class Symbol{

	EnumId idType;

	EnumVar varType;

	int rows, cols;

	String lexeme;

	public Symbol( EnumId idType_tmp, EnumVar varType_tmp, int rows_tmp, int cols_tmp, String lexeme_tmp ){
	
		init( idType_tmp, varType_tmp, rows_tmp, cols_tmp, lexeme_tmp );

	}

	public Symbol( EnumId idType_tmp, EnumVar varType_tmp, String lexeme_tmp ){
		
		init( idType_tmp, varType_tmp, lexeme_tmp );

	}

	public Symbol( EnumId idType_tmp, String lexeme_tmp ){
		
		init( idType_tmp, lexeme_tmp );

	}

	public void init( EnumId idType_tmp, EnumVar varType_tmp, int rows_tmp, int cols_tmp, String lexeme_tmp ){
		
		idType = idType_tmp;

		varType = varType_tmp;

		rows = rows_tmp;

		cols = cols_tmp;

		lexeme = lexeme_tmp;

	}

	public void init( EnumId idType_tmp, EnumVar varType_tmp, String lexeme_tmp ){
		
		assert( idType_tmp == EnumId.VARIABLE 
				&& varType_tmp != EnumVar.NULL );

		idType = idType_tmp;

		varType = varType_tmp;

		rows = -1;

		cols = -1;

		lexeme = lexeme_tmp;

	}

	public void init( EnumId idType_tmp, String lexeme_tmp ){
		
		assert( idType_tmp != EnumId.ARRAY
				&& idType_tmp != EnumId.VARIABLE );

		idType = idType_tmp;

		varType = EnumVar.NULL;

		rows = -1;

		cols = -1;

		lexeme = lexeme_tmp;

	}

	public void print(){

		if( idType == EnumId.ARRAY ){

			System.out.println("idType: " + idType.toString() + " lexeme: " + lexeme + " varType: " + varType.toString() 
					+ " rows: " + rows + " cols: " + cols );
		
		}else if( idType == EnumId.VARIABLE ){

			System.out.println("idType: " + idType.toString() + " lexeme: " + lexeme + " varType: " + varType.toString() );

		}else{

			System.out.println("idType: " + idType.toString() + " lexeme: " + lexeme );

		}

	}

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

