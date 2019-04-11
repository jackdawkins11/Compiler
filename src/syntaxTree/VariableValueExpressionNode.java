
package syntaxTree;

import variableType.EnumStandardType;
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

	@Override
	public EnumStandardType getStandardType(){

		return variable.getStandardType();

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "VariableValueExpressionNode."
			+ " Standard Type: " + getStandardType().toString()
			+ "\n"
			+ arrayIndex.indentedToString( level + 1 )
			+ variable.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips( String indent ){

		String answer = indent + "#VariableValueExpressionNode\n";

		answer += indent + "#end VariableValueExpressionNode\n";

		return answer;

	}

}


