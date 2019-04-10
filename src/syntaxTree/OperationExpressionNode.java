
package syntaxTree;

import variableType.EnumStandardType;

/*
 * Two expressions
 * along with an
 * operation between
 * them.
 */

public class OperationExpressionNode extends ExpressionNode {

	//////////////////
	//     Data     //
	//////////////////

	private ExpressionNode leftExpression;

	private String operation;
	
	private ExpressionNode rightExpression;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public OperationExpressionNode( ExpressionNode leftExpressionTmp,
			String operationTmp,
			ExpressionNode rightExpressionTmp ) throws Exception {

		leftExpression = leftExpressionTmp;

		operation = operationTmp;

		rightExpression = rightExpressionTmp;

		if( leftExpression.getStandardType()
				!= rightExpression.getStandardType() ){

			throw new Exception( "Types do not match across operation." );

		}

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	@Override
	public EnumStandardType getStandardType(){

		return leftExpression.getStandardType();

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "OperationExpressionNode."
			+ " Operation: " + operation
			+ " Standard Type: " + getStandardType().toString()
			+ "\n"
			+ leftExpression.indentedToString( level + 1 )
			+ rightExpression.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips( String indent ){

		//add code for first expression
		String answer = leftExpression.toMips( indent );

		//add code for second expression
		answer += rightExpression.toMips( indent );

		if( leftExpression.getStandardType() == EnumStandardType.REAL ){

			//code to put expressions into $f11, $f12

			answer += indent + "l.s $f11, 4($sp) \n"
				+ indent + "l.s $f12, ($sp) \n"
				+ indent + "addi $sp, $sp, 8\n";

			//code to put operation onto stack

			if( operation == "+" ){

				answer += indent + "add $f10, $f11, $f12 #operation result in $f10\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "s.s $f10, ($sp) #put on stack\n";

			}else if( operation == "-" ){

				answer += indent + "sub $f10, $f11, $f12 #operation result in $f10\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "s.s $f10, ($sp) #put on stack\n";

			}else if( operation == "or" ){

				answer += indent + "or $f10, $f11, $f12 #operation result in $f10\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "s.s $f10, ($sp) #put on stack\n";

			}else if( operation == "*" ){

				answer += indent + "mult $f11, $f12 #operation result in $LO\n"
					+ indent + "mflo $f10 #result in $f10\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "s.s $f10, ($sp) #put on stack\n";

			}else if( operation == "/" ){

				answer += indent + "div $f11, $f12 #operation result in $LO\n"
					+ indent + "mflo $f10 #result in $f10\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "s.s $f10, ($sp) #put on stack\n";

			}else if( operation == "mod" ){

				answer += indent + "div $f11, $f12 #operation result in $LO\n"
					+ indent + "mfhi $f10 #result in $f10\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "s.s $f10, ($sp) #put on stack\n";

			}else if( operation == "and" ){

				answer += indent + "and $f10, $f11, $f12 #operation result in $f10\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "s.s $f10, ($sp) #put on stack\n";

			}

		}else{

			//code to put expressions into $t0, $t1

			answer += indent + "lw $t1, 4($sp) \n"
				+ indent + "lw $t0, ($sp) \n"
				+ indent + "addi $sp, $sp, 8\n";

			//code to put operation onto stack
			
			if( operation == "+" ){

				answer += indent + "add $t2, $t1, $t0 #operation result in $t2\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "-" ){

				answer += indent + "sub $t2, $t1, $t0 #operation result in $t2\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "or" ){

				answer += indent + "or $t2, $t1, $t0 #operation result in $t2\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "*" ){

				answer += indent + "mult $t1, $t0 #operation result in $LO\n"
					+ indent + "mflo $t2 #result in $t2\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "/" ){

				answer += indent + "div $t1, $t0 #operation result in $LO\n"
					+ indent + "mflo $t2 #result in $t2\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "mod" ){

				answer += indent + "div $t1, $t0 #operation result in $LO\n"
					+ indent + "mfhi $t2 #result in $t2\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "and" ){

				answer += indent + "and $t2, $t1, $t0 #operation result in $t2\n"
					+ indent + "addi $sp, $sp, -4\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}

		}

		return answer;

	}

}


