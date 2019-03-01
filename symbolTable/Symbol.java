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

	String lexeme;

	public Symbol( EnumId idType_tmp, EnumVar varType_tmp, String lexeme_tmp ){
		
		idType = idType_tmp;

		varType = varType_tmp;

		lexeme = lexeme_tmp;

	}

	public EnumId getIdType(){
		return idType;
	}

	public EnumVar getVarType(){
		return varType;
	}

	public String getLexeme(){
		return lexeme;
	}

};

