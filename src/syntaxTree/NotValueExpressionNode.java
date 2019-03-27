
package syntaxTree;

/*
 * An expression
 * that is negated.
 */

public class NotValueExpressionNode extends ExpressionNode {

	//////////////////
	//     Data     //
	//////////////////

	ExpressionNode expression;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public NotValueExpressionNode( ExpressionNode expressionTmp ){

		expression = expressionTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "NotValueExpressionNode"
			+ "\n"
			+ expression.indentedToString( level + 1 );

		return answer;

	}
}


