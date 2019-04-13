
package syntaxTree;

/*
 * A statement
 * that reads
 * to a variable.
 */

public class ReadStatementNode extends StatementNode {

	//////////////////
	//     Data     //
	//////////////////

	private VariableNode variable;

	private ExpressionNode arrayOffset;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public VariableAssignmentStatementNode( VariableNode variableTmp,
			ExpressionNode arrayOffsetTmp ){

		variable = variableTmp;

		arrayOffset = arrayOffsetTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////
		
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "ReadStatementNode.\n"
			+ arrayOffset.indentedToString( level + 1 )
			+ variable.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips( String indent ){

		String answer = indent + "#ReadStatementNode\n";

		answer += indent + "#end ReadStatementNode\n";

		return answer;

	}
}


