
package syntaxTree;

import java.util.Vector;

public class FunctionValueExpressionNode extends ExpressionNode {

	private FunctionNode function;
	private Vector< ExpressionNode > parameters;

	public FunctionValueExpressionNode( FunctionNode functionTmp, Vector< ExpressionNode > parametersTmp ){

		function = functionTmp;

		parameters = parametersTmp;

	}

	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "FunctionValueExpressionNode. Name: " + function.getName() + "\n";
		
		for( int i = 0; i < parameters.size(); i++){

			answer += parameters.get( i ).indentedToString( level + 1 );

		}

		return answer;

	}
}


