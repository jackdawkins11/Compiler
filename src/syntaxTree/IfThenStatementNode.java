
package syntaxTree;

/*
 * An if-then
 * test expression
 * and two statements.
 */

public class IfThenStatementNode extends StatementNode {

	//////////////////
	//     Data     //
	//////////////////

	private ExpressionNode testExpression;
	
	private StatementNode thenStatement;
	
	private StatementNode elseStatement;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public IfThenStatementNode( ExpressionNode testExpressionTmp,
			StatementNode thenStatementTmp,
			StatementNode elseStatementTmp ){

		testExpression = testExpressionTmp;

		thenStatement = thenStatementTmp;

		elseStatement = elseStatementTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////
	
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "IfThenStatementNode."
			+ "\n"
			+ testExpression.indentedToString( level + 1 )
			+ thenStatement.indentedToString( level + 1 )
			+ elseStatement.indentedToString( level + 1 );

		return answer;

	}

}


