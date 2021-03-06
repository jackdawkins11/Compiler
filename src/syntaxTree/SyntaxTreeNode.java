
package syntaxTree;

/*
 * The base class
 * for all nodes.
 */

public abstract class SyntaxTreeNode{

	///////////////////////
	//     Functions     //
	///////////////////////

	protected String indentation( int level ){

		String answer = "";

		if( 0 < level ){

			answer = "|-- ";

		}

		for( int indent = 1; indent < level; indent++){

			answer += "--- ";

		}

		return answer;

	}

}
