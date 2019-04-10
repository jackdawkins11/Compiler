
package syntaxTree;

/*
 * An if-then
 * test expression
 * and two statements.
 */

public class IfThenStatementNode extends StatementNode {

	//////////////////
	//     Data     //
	//////////////////

	private ExpressionNode testExpression;
	
	private StatementNode thenStatement;
	
	private StatementNode elseStatement;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public IfThenStatementNode( ExpressionNode testExpressionTmp,
			StatementNode thenStatementTmp,
			StatementNode elseStatementTmp ){

		testExpression = testExpressionTmp;

		thenStatement = thenStatementTmp;

		elseStatement = elseStatementTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////
	
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "IfThenStatementNode."
			+ "\n"
			+ testExpression.indentedToString( level + 1 )
			+ thenStatement.indentedToString( level + 1 )
			+ elseStatement.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips( String indent ){

		String answer = indent + "#IfThenStatementNode\n";

		//get code that puts expression onto stack
		answer += testExpression.toMips( indent );

		//add code that jumps to else if test expression is 0
		answer += indent + "lw $t0, 0($sp) #put expression into $t0\n"
			+ indent + "addi $sp, $sp, 4 #pop stack\n"
			+ indent + "beq $t0, $zero, Else #if expression is 0 jump to Else\n";

		//add then code
		answer += thenStatement.toMips( indent )
		        + indent + "j Endif \n";

		//add else code
		answer += indent + "Else:\n"
		        + elseStatement.toMips( indent );

		//add Endif
		answer += indent + "Endif:\n";

		return answer;

	}

}


