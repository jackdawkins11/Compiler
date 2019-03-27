
package syntaxTree;

public class VariableValueExpressionNode extends ExpressionNode {

	private StandardVariableNode variable;

	public VariableValueExpressionNode( StandardVariableNode variableTmp ){

		variable = variableTmp;

	}

	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "VariableValueExpressionNode.\n";

		answer += variable.indentedToString( level + 1 );

		return answer;

	}
}


