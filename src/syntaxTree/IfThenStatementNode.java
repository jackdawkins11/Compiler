
package syntaxTree;

/*
 * An if-then statement is a
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

		String answer = indent + "#IfThenStatementNode\n"
			+ testExpression.toMips( indent )
			+ indent + "lw $t0, ($sp) #put expression into $t0\n"
			+ indent + "addi $sp, $sp, 4 #pop stack\n"
			+ indent + "beq $t0, $zero, Else #if expression is 0 jump to Else\n"
		        + thenStatement.toMips( indent )
		        + indent + "j Endif \n"
			+ indent + "Else:\n"
		        + elseStatement.toMips( indent )
			+ indent + "Endif:\n"
			+ indent + "#end IfThenStatementNode\n";

		return answer;

	}

}


