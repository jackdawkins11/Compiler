
package scanner;

/*
 *
 * A token is a string and its type.
 */

public class Token{

	private EnumToken type;

	private String string;	

	public Token( EnumToken type_tmp, String string_tmp ){ type = type_tmp; string = string_tmp; }

	public EnumToken getType(){ return type; }

	public String getString(){ return string ; }

};

