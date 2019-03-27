
package syntaxTree;

public abstract class SyntaxTreeNode{

	public abstract String indentedToString( int level );

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