
package syntaxTree;

/*
 * A while
 * do statement.
 */

public class WhileDoStatementNode extends StatementNode {

	//////////////////
	//     Data     //
	//////////////////

	private ExpressionNode testExpression;

	private StatementNode doStatement;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public WhileDoStatementNode( ExpressionNode testExpressionTmp,
			StatementNode doStatementTmp ){

		testExpression = testExpressionTmp;

		doStatement = doStatementTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////
	
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "WhileDoStatementNode."
			+ "\n"
			+ testExpression.indentedToString( level + 1 )
			+ doStatement.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips( String indent ){

		String answer = indent + "#WhileDoStatementNode\n";

		return answer;

	}

}


