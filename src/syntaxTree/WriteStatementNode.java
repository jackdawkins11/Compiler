
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

	public String toMips( String indent ){

		String answer = indent + "#WriteStatementNode\n";

		//add expression code that leaves result in stack
		answer += writeExpression.toMips( indent );

		if( writeExpression.getStandardType() == EnumStandardType.REAL ){

			//append code for printing float

			answer += indent + "l.s $f12, 0($sp) #move double from stack to $f12\n"
				+ indent + "addi $sp, $sp, 4 #pop stack\n"
				+ indent + "li $v0, 2 #syscall 2\n"
				+ indent + "syscall\n";

		}else{

			//append code for printing int
			
			answer += indent + "lw $a0, 0($sp) #move int from stack to $a0\n"
				+ indent + "addi $sp, $sp, 4 #pop stack\n"
				+ indent + "li $v0, 2 #syscall 1\n"
				+ indent + "syscall\n";

		}

		answer += indent + "#end WriteStatementNode\n";

		return answer;

	}

}


