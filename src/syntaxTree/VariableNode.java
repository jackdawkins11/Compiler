
package syntaxTree;

import variableType.*;

/*
 * This class represents a
 * variable.
 */

public class VariableNode extends SyntaxTreeNode {

	//////////////////
	//     Data     //
	//////////////////

	VariableType variableType;

	String name,
	       registerName;

	//////////////////////////
	//     Constructors     //
	//////////////////////////

	public VariableNode( String nameTmp,
			VariableType variableTypeTmp){

		variableType = variableTypeTmp;

		name = nameTmp;

	}

	////////////////////////////
	//     Public Methods     //
	////////////////////////////

	public String getName(){

		return name;

	}

	public String getRegisterName(){

		return registerName;

	}

	public EnumStandardType getStandardType(){

		return variableType.getStandardType();

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "VariableNode."
			+ " Name: " + name
			+ " Standard Type: " + variableType.getStandardType().toString()
			+ " Begin Index: " + variableType.getBeginIndex()
			+ " End Index: " + variableType.getEndIndex()
			+ "\n";

		return answer;

	}
	
	public String toMips( String indent,
			RegisterInfo registerInfo ){

		registerName = registerInfo.nextRegister();
		
		return indent + "#VariableNode Name: " name + " Register: " + registerName + "\n";
	
	}

}


