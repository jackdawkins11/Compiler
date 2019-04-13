
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
			ExpressionNode rightExpressionTmp ){

		leftExpression = leftExpressionTmp;

		operation = operationTmp;

		rightExpression = rightExpressionTmp;

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
	public String toMips(){

		String answer = "     #OperationExpressionNode\n";

		//add code for first expression
		answer += leftExpression.toMips();

		//add code for second expression
		answer += rightExpression.toMips();

		if( getStandardType() == EnumStandardType.REAL ){

			System.out.println("soon..");

			System.exit( 1 );

		}else{

			//code to put expressions into $t0, $t1

			answer += "     lw $t0, 4($sp) \n"
				+ "     lw $t1, ($sp) \n"
				+ "     addi $sp, $sp, 4 #save room for one word on stack\n";

			//code to put operation onto stack
			
			if( operation == "+" ){

				answer += "     add $t2, $t0, $t1 #operation result in $t2\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation == "-" ){

				answer += "     sub $t2, $t0, $t1 #operation result in $t2\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation == "or" ){
				
				answer += "     bne $t0, $zero, success\n"
					+ "     bne $t1, $zero, success\n"
					+ "     li $t2, 0 #$t2 is false\n"
					+ "     j endIf\n"
					+ "     success:\n"
					+ "     li $t2, 1 #$t2 is true\n"
					+ "     endIf:\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation == "*" ){

				answer += "     mult $t0, $t1 #operation result in $LO\n"
					+ "     mflo $t2 #result in $t2\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation == "/" ){

				answer += "     div $t0, $t1 #operation result in $LO\n"
					+ "     mflo $t2 #result in $t2\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation == "mod" ){

				answer += "     div $t0, $t1 #operation result in $LO\n"
					+ "     mfhi $t2 #result in $t2\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation == "and" ){

				answer += "     beq $t0, $zero, fail\n"
					+ "     beq $t1, $zero, fail\n"
					+ "     li $t2, 1 #$t2 is true\n"
					+ "     j endIf\n"
					+ "     fail:\n"
					+ "     li $t2, 0 #$t2 is false\n"
					+ "     endIf:\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation == "=" ){

				answer += "     bne $t0, $t1, notEqual\n"
					+ "     li $t2, 1 #$t2 is true\n"
					+ "     j endIf\n"
					+ "     notEqual:\n"
					+ "     li $t2, 0 #$t2 is false\n"
					+ "     endIf:\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation == "<=" ){

				answer += "     bne $t0, $t1, notEqual\n"
					+ "     li $t2, 1 #$t2 is true\n"
					+ "     j endIf\n"
					+ "     notEqual:\n"
					+ "     slt $t2, $t0, $t1 \n"
					+ "     endIf:\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation == ">=" ){

				answer += "     bne $t0, $t1, notEqual\n"
					+ "     li $t2, 1 #$t2 is true\n"
					+ "     j endIf\n"
					+ "     notEqual:\n"
					+ "     slt $t2, $t1, $t0 \n"
					+ "     endIf:\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation == "<" ){

				answer += "     slt $t2, $t0, $t1 \n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation == ">" ){

				answer += "     slt $t2, $t1, $t0 \n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}


		}

		answer += "     #end OperationExpressionNode\n";

		return answer;

	}

}


