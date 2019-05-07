
package syntaxTree;

import variableType.*;

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
	
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "ReturnStatementNode."
			+ "\n"
			+ returnExpression.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips(){

		String answer = "     #ReturnStatementNode\n";

		answer += returnExpression.toMips();

		if( returnExpression.getStandardType()
				== EnumStandardType.REAL ){

			answer += "     lwc1 $f0, ($sp) #$f0 is floating point return\n"
				+ "     addi $sp, $sp, 4 #pop stack\n"
				;

		}else{

			answer += "     lw $v0, ($sp) #$v0 is return register\n"
				+ "     addi $sp, $sp, 4 #pop stack\n"
				;

		}
		
		answer += "     #end ReturnStatementNode\n";

		return answer;
	
	}

}


