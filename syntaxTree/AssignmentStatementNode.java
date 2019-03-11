
package syntaxTree;

public class AssignmentStatementNode extends StatementNode{

	private VariableNode lValue;

	private ExpressionNode expression;

	public VariableNode getLValue(){ return lValue; }

	public ExpressionNode getExpression(){ return expression; }

	public void setLValue( VariableNode lValueTmp ){ lValue = lValueTmp; }

	public void setExpression( ExpressionNode expressionTmp ){ expression = expressionTmp; }

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );
		
		answer += "Assignment\n";

		answer += lValue.indentedToString( level + 1 );

		answer += expression.indentedToString( level + 1 );

		return answer;

	}

}
