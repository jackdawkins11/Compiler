
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
	public String toMips(){

		String answer = "     #WhileDoStatementNode\n";

		answer += "     topOfLoop:\n"
			+ doStatement.toMips()
			+ testExpression.toMips()
			+ "     lw $t0, ($sp) #test expression in $t0\n"
			+ "     addi $sp, $sp, 4 #pop stack\n"
			+ "     bne $t0, $zero, topOfLoop #if $t0 goto topOfLoop\n"
			;

		answer += "     #end WhileDoStatementNode\n";

		return answer;

	}

}


