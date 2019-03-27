
package scanner;

/*
 * A token consists of a lexeme
 * and a type.
 */

public class Token{

	//////////////////
	//     Data     //
	//////////////////

	private EnumToken type;

	private String string;	

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public Token( EnumToken type_tmp, String string_tmp ){ type = type_tmp; string = string_tmp; }

	/////////////////////
	//     Getters     //
	/////////////////////

	public EnumToken getType(){ return type; }

	public String getString(){ return string ; }

};

