
package syntaxTree;

import scanner.*;
import variableType.*;

public class StandardVariableNode extends VariableNode {

	String name;
	
	VariableType variableType;
	
	public StandardVariableNode( String nameTmp, VariableType variableTypeTmp ){

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

		answer += "StandardVariableNode. Name: " + name + "\n";

		return answer;

	}

}
