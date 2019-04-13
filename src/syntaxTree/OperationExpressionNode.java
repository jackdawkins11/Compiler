
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

		if( ( leftExpression.getStandardType()
			!= rightExpression.getStandardType() )
		|| ( ( operation == "or" || operation == "and" )
			&& leftExpression.getStandardType() == EnumStandardType.REAL )
				){

			throw new Exception( "Invalid operation or types." );

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

		String answer = indent + "#OperationExpressionNode\n";

		//add code for first expression
		answer += leftExpression.toMips( indent );

		//add code for second expression
		answer += rightExpression.toMips( indent );

		if( getStandardType() == EnumStandardType.REAL ){

			System.out.println("invalid ..");

			System.exit( 1 );

		}else{

			//code to put expressions into $t0, $t1

			answer += indent + "lw $t0, 4($sp) \n"
				+ indent + "lw $t1, ($sp) \n"
				+ indent + "addi $sp, $sp, 4 #save room for one word on stack\n";

			//code to put operation onto stack
			
			if( operation == "+" ){

				answer += indent + "add $t2, $t0, $t1 #operation result in $t2\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "-" ){

				answer += indent + "sub $t2, $t0, $t1 #operation result in $t2\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "or" ){
				
				answer += indent + "bne $t0, $zero, success\n"
					+ indent + "bne $t1, $zero, success\n"
					+ indent + "li $t2, 0 #$t2 is false\n"
					+ indent + "j endIf\n"
					+ indent + "success:\n"
					+ indent + "li $t2, 1 #$t2 is true\n"
					+ indent + "endIf:\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "*" ){

				answer += indent + "mult $t0, $t1 #operation result in $LO\n"
					+ indent + "mflo $t2 #result in $t2\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "/" ){

				answer += indent + "div $t0, $t1 #operation result in $LO\n"
					+ indent + "mflo $t2 #result in $t2\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "mod" ){

				answer += indent + "div $t0, $t1 #operation result in $LO\n"
					+ indent + "mfhi $t2 #result in $t2\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "and" ){

				answer += indent + "beq $t0, $zero, fail\n"
					+ indent + "beq $t1, $zero, fail\n"
					+ indent + "li $t2, 1 #$t2 is true\n"
					+ indent + "j endIf\n"
					+ indent + "fail:\n"
					+ indent + "li $t2, 0 #$t2 is false\n"
					+ indent + "endIf:\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "=" ){

				answer += indent + "bne $t0, $t1, notEqual\n"
					+ indent + "li $t2, 1 #$t2 is true\n"
					+ indent + "j endIf\n"
					+ indent + "notEqual:\n"
					+ indent + "li $t2, 0 #$t2 is false\n"
					+ indent + "endIf:\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "<=" ){

				answer += indent + "bne $t0, $t1, notEqual\n"
					+ indent + "li $t2, 1 #$t2 is true\n"
					+ indent + "j endIf\n"
					+ indent + "notEqual:\n"
					+ indent + "slt $t2, $t0, $t1 \n"
					+ indent + "endIf:\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == ">=" ){

				answer += indent + "bne $t0, $t1, notEqual\n"
					+ indent + "li $t2, 1 #$t2 is true\n"
					+ indent + "j endIf\n"
					+ indent + "notEqual:\n"
					+ indent + "slt $t2, $t1, $t0 \n"
					+ indent + "endIf:\n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == "<" ){

				answer += indent + "slt $t2, $t0, $t1 \n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}else if( operation == ">" ){

				answer += indent + "slt $t2, $t1, $t0 \n"
					+ indent + "sw $t2, ($sp) #put on stack\n";

			}


		}

		answer += indent + "#end OperationExpressionNode\n";

		return answer;

	}

}


