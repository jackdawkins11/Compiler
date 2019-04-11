
package syntaxTree;

import java.util.ArrayList;

/*
 * A compound statement
 * node is just an array
 * list of StatementNodes.
 */

public class CompoundStatementNode extends StatementNode{

	//////////////////
	//     Data     //
	//////////////////

	private ArrayList< StatementNode > statements = new ArrayList< StatementNode >();

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	public void addStatement( StatementNode newStatement ){

		statements.add( newStatement );

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "CompoundStatementNode."
			+ "\n";

		for( StatementNode cStatement : statements ){

			answer += cStatement.indentedToString( level + 1 );

		}

		return answer;

	}

	@Override
	public String toMips( String indent ){

		String answer = indent + "#CompoundStatementNode\n";

		for( StatementNode statement : statements ){

			answer += statement.toMips( indent );

		}

		answer += indent + "#End CompoundStatementNode\n";

		return answer;

	}

}

