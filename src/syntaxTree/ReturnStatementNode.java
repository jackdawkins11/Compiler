
package syntaxTree;

/*
 * A node
 * that represents
 * a return statement.
 */

public class ReturnStatementNode extends StatementNode {

	//////////////////
	//     Data     //
	//////////////////

	ExpressionNode returnExpression;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public ReturnStatementNode( ExpressionNode returnExpressionTmp ){

		returnExpression = returnExpressionTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////
	
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "ReturnStatementNode."
			+ "\n"
			+ returnExpression.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips( String indent ){

		return "";

	}

}


