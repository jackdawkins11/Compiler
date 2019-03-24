
package syntaxTree;

public class NotValueExpressionNode extends ExpressionNode {

	ExpressionNode invertedExpression;

	public NotValueExpressionNode( ExpressionNode expressionTmp ){

		invertedExpression = expressionTmp;

	}

	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "NotValueExpressionNode\n";

		answer += invertedExpression.indentedToString( level + 1 );

		return answer;

	}
}


