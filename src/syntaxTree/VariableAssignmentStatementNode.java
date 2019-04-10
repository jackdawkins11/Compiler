
package syntaxTree;

/*
 * This represents
 * an assignment
 * statement.
 */

public class VariableAssignmentStatementNode extends StatementNode{

	//////////////////
	//     Data     //
	//////////////////

	private VariableNode variable;

	private ExpressionNode arrayOffset;

	private ExpressionNode rValue;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public VariableAssignmentStatementNode( VariableNode variableTmp,
			ExpressionNode arrayOffsetTmp,
			ExpressionNode rValueTmp ){

		variable = variableTmp;

		arrayOffset = arrayOffsetTmp;

		rValue = rValueTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "VariableAssignmentStatementNode."
			+ "\n"
			+ arrayOffset.indentedToString( level + 1 )
			+ variable.indentedToString( level + 1 )
			+ rValue.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips( String indent ){

		return "";

	}

}
