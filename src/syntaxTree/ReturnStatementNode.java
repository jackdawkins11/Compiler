
package syntaxTree;

public class ReturnStatementNode extends StatementNode {

	ExpressionNode returnExpression;

	public ReturnStatementNode( ExpressionNode returnExpressionTmp ){

		returnExpression = returnExpressionTmp;

	}
	
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "ReturnStatementNode\n";

		answer += returnExpression.indentedToString( level + 1 );

		return answer;

	}

}


