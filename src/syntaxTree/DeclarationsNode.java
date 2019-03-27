
package syntaxTree;

import java.util.ArrayList;

/*
 * A DeclarationsNode is
 * just an array of VariableNodes.
 */

public class DeclarationsNode extends SyntaxTreeNode {

	//////////////////
	//     Data     //
	//////////////////

	private ArrayList< VariableNode > variables = new ArrayList< VariableNode >();

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	public void addVariable( VariableNode newVariable ){

		variables.add( newVariable );

	}

	public void addVariables( DeclarationsNode other ){

		for( int i = 0; i < other.variables.size(); i++){

			variables.add( other.variables.get( i ) );

		}

	}
	
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
		       + "DeclarationsNode."
		       + "\n";

		for( VariableNode variable : variables ){

			answer += variable.indentedToString( level + 1 );

		}

		return answer;

	}

}
