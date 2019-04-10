
package syntaxTree;

import variableType.EnumStandardType;

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

	@Override
	public EnumStandardType getStandardType(){

		return expression.getStandardType();

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "NotValueExpressionNode."
			+ " Standard Type: " + getStandardType().toString()
			+ "\n"
			+ expression.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips( String indent ){

		return "";

	}

}


