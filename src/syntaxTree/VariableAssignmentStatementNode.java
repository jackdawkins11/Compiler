
package syntaxTree;

public class VariableAssignmentStatementNode extends StatementNode{

	private StandardVariableNode variable;

	private ExpressionNode rValue;

	public VariableAssignmentStatementNode( StandardVariableNode variableTmp, ExpressionNode rValueTmp ){

		variable = variableTmp;

		rValue = rValueTmp;

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );
		
		answer += "VariableAssignmentStatementNode.\n";

		answer += variable.indentedToString( level + 1 );

		answer += rValue.indentedToString( level + 1 );

		return answer;

	}

}
