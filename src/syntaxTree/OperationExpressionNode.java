
package syntaxTree;

import variableType.EnumStandardType;

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

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	@Override
	public EnumStandardType getStandardType(){

		if( leftExpression.getStandardType()
				== rightExpression.getStandardType() ){

			return leftExpression.getStandardType();

		}else{

			//round to integer
			
			assert( ( leftExpression.getStandardType()
					== EnumStandardType.INTEGER )
				|| ( rightExpression.getStandardType()
					== EnumStandardType.INTEGER ) );
			
			return EnumStandardType.INTEGER;

		}

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "OperationExpressionNode."
			+ " Operation: " + operation
			+ " Standard Type: " + getStandardType().toString()
			+ "\n"
			+ leftExpression.indentedToString( level + 1 )
			+ rightExpression.indentedToString( level + 1 );

		return answer;

	}

}


