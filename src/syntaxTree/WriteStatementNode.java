
package syntaxTree;

public class WriteStatementNode extends StatementNode {

	ExpressionNode writeExpression;

	public WriteStatementNode( ExpressionNode writeExpressionTmp ){

		writeExpression = writeExpressionTmp;

	}
	
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "WriteStatementNode.\n";

		answer += writeExpression.indentedToString( level + 1 );

		return answer;

	}

}


