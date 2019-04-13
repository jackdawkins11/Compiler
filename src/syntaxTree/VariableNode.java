
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

	String name;

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
	
	public EnumStandardType getStandardType(){

		return variableType.getStandardType();

	}

	public boolean isArray(){

		return variableType.isArray();

	}

	public int getSize(){

		return variableType.getEndIndex() - variableType.getBeginIndex();

	}

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
	
}


