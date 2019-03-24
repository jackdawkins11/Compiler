
package syntaxTree;

import java.util.ArrayList;

public class CompoundStatementNode extends StatementNode{

	private ArrayList< StatementNode > statements = new ArrayList< StatementNode >();

	public void addStatement( StatementNode newStatement ){

		statements.add( newStatement );

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "CompoundStatementNode.\n";

		for( StatementNode cStatement : statements ){

			answer += cStatement.indentedToString( level + 1 );

		}

		return answer;

	}

}




