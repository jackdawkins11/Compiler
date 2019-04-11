
package syntaxTree;

/*
 * A while
 * do statement.
 */

public class WhileDoStatementNode extends StatementNode {

	//////////////////
	//     Data     //
	//////////////////

	private ExpressionNode testExpression;

	private StatementNode doStatement;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public WhileDoStatementNode( ExpressionNode testExpressionTmp,
			StatementNode doStatementTmp ){

		testExpression = testExpressionTmp;

		doStatement = doStatementTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////
	
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "WhileDoStatementNode."
			+ "\n"
			+ testExpression.indentedToString( level + 1 )
			+ doStatement.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips( String indent ){

		String answer = indent + "#WhileDoStatementNode\n";

		//top of loop
		answer += indent + "topOfLoop:\n";

		//loop body
		answer += doStatement.toMips( indent );

		//test to jump to topOfLoop
		answer += testExpression.toMips( indent )
			+ indent + "lw $t0, ($sp) #test expression in $t0\n"
			+ indent + "addi $sp, $sp, 4 #pop stack\n"
			+ indent + "bne $t0, $zero, topOfLoop #if $t0 goto topOfLoop\n"
			;

		answer += indent + "#end WhileDoStatementNode\n";

		return answer;

	}

}


