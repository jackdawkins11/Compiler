
package syntaxTree;

/*
 * A variable
 * being used as
 * a factor.
 */

public class VariableValueExpressionNode extends ExpressionNode {

	//////////////////
	//     Data     //
	//////////////////

	private VariableNode variable;

	private ExpressionNode arrayIndex;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public VariableValueExpressionNode( VariableNode variableTmp, ExpressionNode arrayIndexTmp ){

		variable = variableTmp;

		arrayIndex = arrayIndexTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "VariableValueExpressionNode."
			+ "\n"
			+ variable.indentedToString( level + 1 );

		return answer;

	}
}


