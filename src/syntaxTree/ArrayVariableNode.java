
package syntaxTree;

import variableType.*;

public class ArrayVariableNode extends VariableNode {

	String name;
	
	VariableType variableType;
	
	public ArrayVariableNode( String nameTmp, VariableType variableTypeTmp ){

		name = nameTmp;
	
		variableType = variableTypeTmp;

	}

	@Override
	public String getName(){

		return name;

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "ArrayVariableNode. Name: " + name + " Rows: " + variableType.getRows() + " Cols: " + variableType.getCols() + "\n";

		return answer;

	}

}
