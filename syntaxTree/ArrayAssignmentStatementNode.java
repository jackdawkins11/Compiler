
package syntaxTree;

public class ArrayAssignmentStatementNode extends StatementNode{

	private ArrayVariableNode array;

	private ExpressionNode arrayOffset, rValue;

	public ArrayAssignmentStatementNode( ArrayVariableNode arrayTmp, ExpressionNode arrayOffsetTmp,
			ExpressionNode rValueTmp ){

		array = arrayTmp;

		arrayOffset = arrayOffsetTmp;

		rValue = rValueTmp;

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );
		
		answer += "ArrayAssignmentStatementNode.\n";

		answer += array.indentedToString( level + 1 );

		answer += arrayOffset.indentedToString( level + 1 );

		answer += rValue.indentedToString( level + 1 );

		return answer;

	}

}
