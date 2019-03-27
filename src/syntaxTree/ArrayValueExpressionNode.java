

package syntaxTree;

public class ArrayValueExpressionNode extends ExpressionNode {

	private ArrayVariableNode array;
	private ExpressionNode offset;

	public ArrayValueExpressionNode( ArrayVariableNode arrayTmp, ExpressionNode offsetTmp ){

		array = arrayTmp;

		offset = offsetTmp;

	}

	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "ArrayValueExpressionNode\n";

		answer += array.indentedToString( level + 1 );

		answer += offset.indentedToString( level + 1 );

		return answer;

	}
}


