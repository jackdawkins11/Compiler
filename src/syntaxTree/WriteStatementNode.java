
package syntaxTree;

import variableType.EnumStandardType;

public class WriteStatementNode extends StatementNode {

	//////////////////
	//     Data     //
	//////////////////

	ExpressionNode writeExpression;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public WriteStatementNode( ExpressionNode writeExpressionTmp ){

		writeExpression = writeExpressionTmp;

	}

	///////////////////////
	//     Functions     //
	///////////////////////

	@Override	
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "WriteStatementNode.\n";

		answer += writeExpression.indentedToString( level + 1 );

		return answer;

	}

	public String toMips(){

		String answer = "     #WriteStatementNode\n";

		//add expression code that leaves result in stack
		answer += writeExpression.toMips();

		if( writeExpression.getStandardType() == EnumStandardType.REAL ){

			//append code for printing float

			answer += "     l.s $f12, 0($sp) #move double from stack to $f12\n"
				+ "     addi $sp, $sp, 4 #pop stack\n"
				+ "     li $v0, 2 #syscall 2\n"
				+ "     syscall\n";

		}else{

			//append code for printing int
			
			answer += "     lw $a0, 0($sp) #move int from stack to $a0\n"
				+ "     addi $sp, $sp, 4 #pop stack\n"
				+ "     li $v0, 1 #syscall 1\n"
				+ "     syscall\n";

		}

		//write newline

		answer += "     li $a0, 0xA\n"
			+ "     li $v0, 11\n"
			+ "syscall\n";

		answer += "     #end WriteStatementNode\n";

		return answer;

	}

}


