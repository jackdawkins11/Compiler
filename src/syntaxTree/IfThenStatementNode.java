
package syntaxTree;

import variableType.*;

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
			StatementNode elseStatementTmp ) throws RuntimeException {

		testExpression = testExpressionTmp;

		thenStatement = thenStatementTmp;

		elseStatement = elseStatementTmp;

		if( testExpression.getStandardType()
				!= EnumStandardType.INTEGER ){

			throw new RuntimeException( "Cannot convert if-then test expression to boolean." );

		}

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
	public String toMips(){

		String answer = 
			  "     #IfThenStatementNode\n"
			+ testExpression.toMips()
			+ "     lw $t0, ($sp) #put expression into $t0\n"
			+ "     addi $sp, $sp, 4 #pop stack\n"
			+ "     beq $t0, $zero, Else #if expression is 0 jump to Else\n"
		        + thenStatement.toMips()
		        + "     j Endif \n"
			+ "     Else:\n"
		        + elseStatement.toMips()
			+ "     Endif:\n"
			+ "     #end IfThenStatementNode\n";

		return answer;

	}

}


