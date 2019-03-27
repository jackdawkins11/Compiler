
package syntaxTree;

public class OperationExpressionNode extends ExpressionNode {

	private ExpressionNode leftExpression;

	private String operation;
	
	private ExpressionNode rightExpression;

	public OperationExpressionNode( ExpressionNode leftExpressionTmp, String operationTmp,
			ExpressionNode rightExpressionTmp ){

		leftExpression = leftExpressionTmp;

		operation = operationTmp;

		rightExpression = rightExpressionTmp;

	}

	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "OperationExpressionNode. Operation: " + operation + "\n";

		answer += leftExpression.indentedToString( level + 1 );

		answer += rightExpression.indentedToString( level + 1 );

		return answer;

	}
}


