
package syntaxTree;

import java.util.Vector;

public class ProcedureStatementNode extends StatementNode{

	private SubProgramNode procedure;
	Vector< ExpressionNode > parameters;

	public ProcedureStatementNode( SubProgramNode procedureTmp ){

		procedure = procedureTmp;

		parameters = new Vector< ExpressionNode >();

	}

	public ProcedureStatementNode( SubProgramNode procedureTmp, 
		Vector< ExpressionNode > parametersTmp ){

		procedure = procedureTmp;

		parameters = parametersTmp;

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "ProcedureStatementNode. Name: " + procedure.getName() + "\n";

		for( int i = 0; i < parameters.size(); i++){

			answer += parameters.get( i ).indentedToString( level + 1 );

		}

		return answer;

	}

}


