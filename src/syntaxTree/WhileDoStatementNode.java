
package syntaxTree;

public class WhileDoStatementNode extends StatementNode {

	private ExpressionNode testExpression;
	private StatementNode doStatement;

	public WhileDoStatementNode( ExpressionNode testExpressionTmp,
			StatementNode doStatementTmp ){

		testExpression = testExpressionTmp;

		doStatement = doStatementTmp;

	}
	
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "whileDoStatementNode\n";

		answer += testExpression.indentedToString( level + 1 );

		answer += doStatement.indentedToString( level + 1 );

		return answer;

	}

}


