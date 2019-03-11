
package syntaxTree;

public class IfStatementNode extends StatementNode {

	private ExpressionNode testExpression;
	private StatementNode thenStatement;
	private StatementNode elseStatement;

	public ExpressionNode getTest(){ return testExpression; }

	public StatementNode getThenStatement(){ return thenStatement; }

	public StatementNode getElseStatement(){ return elseStatement; }

	public void setTest( ExpressionNode testTmp ){ testExpression = testTmp; }

	public void setThenStatement( StatementNode thenStatementTmp ){ thenStatement = thenStatementTmp; }

	public void setElseStatement( StatementNode elseStatementTmp ){ elseStatement = elseStatementTmp; }

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "if\n";

		answer += testExpression.indentedToString( level + 1 );

		answer += thenStatement.indentedToString( level + 1 );

		answer += elseStatement.indentedToString( level + 1 );

		return answer;

	}

}


