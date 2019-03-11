
package syntaxTree;

import java.util.ArrayList;

public class DeclarationsNode extends SyntaxTreeNode {

	private ArrayList< VariableNode > variables = new ArrayList< VariableNode >();

	public void addVariable( VariableNode newVariable ){

		variables.add( newVariable );

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "Declarations\n";

		for( VariableNode variable : variables ){

			answer += variable.indentedToString( level + 1 );

		}

		return answer;

	}

}
