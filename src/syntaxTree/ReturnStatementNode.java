
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

		String answer = indent + "#ReturnStatementNode\n";

		answer += returnExpression.toMips( indent );

		if( returnExpression.getStandardType()
				== EnumStandardType.REAL ){

			answer += "lw $f0, ($sp) #$f0 is floating point return\n"
				+ "addi $sp, $sp, 4 #pop stack\n"
				;

		}else{

			answer += "lw $v0, ($sp) #$v0 is return register\n"
				+ "addi $sp, $sp, 4 #pop stack\n"
				;

		}
		
		answer += indent + "#end ReturnStatementNode\n";

		return answer;
	
	}

}


