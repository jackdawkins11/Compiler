
package syntaxTree;

/*
 * Two expressions
 * along with an
 * operation between
 * them.
 */

public class OperationExpressionNode extends ExpressionNode {

	//////////////////
	//     Data     //
	//////////////////

	private ExpressionNode leftExpression;

	private String operation;
	
	private ExpressionNode rightExpression;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public OperationExpressionNode( ExpressionNode leftExpressionTmp,
			String operationTmp,
			ExpressionNode rightExpressionTmp ){

		leftExpression = leftExpressionTmp;

		operation = operationTmp;

		rightExpression = rightExpressionTmp;

	}

	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "OperationExpressionNode."
			+ " Operation: " + operation 
			+ "\n"
			+ leftExpression.indentedToString( level + 1 )
			+ rightExpression.indentedToString( level + 1 );

		return answer;

	}
}


