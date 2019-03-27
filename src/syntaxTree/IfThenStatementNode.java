
package syntaxTree;

public class IfThenStatementNode extends StatementNode {

	private ExpressionNode testExpression;
	private StatementNode thenStatement;
	private StatementNode elseStatement;

	public IfThenStatementNode( ExpressionNode testExpressionTmp,
			StatementNode thenStatementTmp,
			StatementNode elseStatementTmp ){

		testExpression = testExpressionTmp;

		thenStatement = thenStatementTmp;

		elseStatement = elseStatementTmp;

	}
	
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "IfThenStatementNode.\n";

		answer += testExpression.indentedToString( level + 1 );

		answer += thenStatement.indentedToString( level + 1 );

		answer += elseStatement.indentedToString( level + 1 );

		return answer;

	}

}


