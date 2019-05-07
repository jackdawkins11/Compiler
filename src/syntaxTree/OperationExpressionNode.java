
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

		if( ( operation.equals("+")
		 || operation.equals("-")
		 || operation.equals("*")
		 || operation.equals("/" ) )
		&& leftExpression.getStandardType()
			== rightExpression.getStandardType() ){

			return leftExpression.getStandardType();

		}else{

			return EnumStandardType.INTEGER;

		}

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
		
		if( leftExpression.getStandardType() == EnumStandardType.REAL
		 	&& leftExpression.getStandardType() == rightExpression.getStandardType() ){
		
			//code to put expressions into $f0, $f1

			answer += "     lwc1 $f0, 4($sp) \n"
				+ "     lwc1 $f1, ($sp) \n"
				+ "     addi $sp, $sp, 4 #save room for one word on stack\n";
	
			if( operation.equals("+" ) ){

				answer += "     add.s $f2, $f0, $f1 #operation result in $f2\n"
					+ "     swc1 $f2, ($sp) #put on stack\n";

			}else if( operation.equals("-" ) ){

				answer += "     sub.s $f2, $f0, $f1 #operation result in $f2\n"
					+ "     swc1 $f2, ($sp) #put on stack\n";

			}else if( operation.equals("*" ) ){

				answer += "     mult.s $f2, $f0, $f1 #operation result in $f2\n"
					+ "     swc1 $f2, ($sp) #put on stack\n";

			}else if( operation.equals("/" ) ){

				answer += "     div $f2, $f0, $f1 #operation result in $f2\n"
					+ "     swc1 $f2, ($sp) #put on stack\n";

			}else if( operation.equals("=" ) ){

				answer += "     c.eq.s $f0, $f1 \n"
					+ "     bc1f notEqual\n"
					+ "     li $f2, 1 #$f2 is true\n"
					+ "     j endIf\n"
					+ "     notEqual:\n"
					+ "     li $f2, 0 #$f2 is false\n"
					+ "     endIf:\n"
					+ "     sw $f2, ($sp) #put on stack\n";

			}else if( operation.equals("<=" ) ){
				
				answer += "     c.le.s $f0, $f1 \n"
					+ "     bc1f notEqual\n"
					+ "     li $f2, 1 #$f2 is true\n"
					+ "     j endIf\n"
					+ "     notEqual:\n"
					+ "     li $f2, 0 #$f2 is false\n"
					+ "     endIf:\n"
					+ "     sw $f2, ($sp) #put on stack\n";

			}else if( operation.equals(">=" ) ){
				
				answer += "     c.le.s $f1, $f0 \n"
					+ "     bc1f notEqual\n"
					+ "     li $f2, 1 #$f2 is true\n"
					+ "     j endIf\n"
					+ "     notEqual:\n"
					+ "     li $f2, 0 #$f2 is false\n"
					+ "     endIf:\n"
					+ "     sw $f2, ($sp) #put on stack\n";

			}else if( operation.equals("<" ) ){
				
				answer += "     c.lt.s $f0, $f1 \n"
					+ "     bc1f notEqual\n"
					+ "     li $f2, 1 #$f2 is true\n"
					+ "     j endIf\n"
					+ "     notEqual:\n"
					+ "     li $f2, 0 #$f2 is false\n"
					+ "     endIf:\n"
					+ "     sw $f2, ($sp) #put on stack\n";


			}else if( operation.equals(">" ) ){
					
				answer += "     c.lt.s $f1, $f0 \n"
					+ "     bc1f notEqual\n"
					+ "     li $f2, 1 #$f2 is true\n"
					+ "     j endIf\n"
					+ "     notEqual:\n"
					+ "     li $f2, 0 #$f2 is false\n"
					+ "     endIf:\n"
					+ "     sw $f2, ($sp) #put on stack\n";
			}

		}else{

			//code to put expressions into $t0, $t1

			if( leftExpression.getStandardType() == EnumStandardType.REAL ){

				answer += "     lwc1 $f0, 4($sp) \n"
					+ "     cvt.w.s $t0, $f0 \n"
					+ "     lw $t1, ($sp) \n";

			}else if( rightExpression.getStandardType() == EnumStandardType.REAL ){

				answer += "     lw $t0, 4($sp) \n"
					+ "     lwc1 $f1, ($sp) \n"
					+ "     cvt.w.s $t1, $f1 \n";

			}else{

				answer += "     lw $t0, 4($sp) \n"
					+ "     lw $t1, ($sp) \n";

			}

			answer += "     addi $sp, $sp, 4 #save room for one word on stack\n";
			
			//code to put operation onto stack
			
			if( operation.equals("+" ) ){

				answer += "     add $t2, $t0, $t1 #operation result in $t2\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation.equals("-" ) ){

				answer += "     sub $t2, $t0, $t1 #operation result in $t2\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation.equals("or" ) ){
				
				answer += "     bne $t0, $zero, success\n"
					+ "     bne $t1, $zero, success\n"
					+ "     li $t2, 0 #$t2 is false\n"
					+ "     j endIf\n"
					+ "     success:\n"
					+ "     li $t2, 1 #$t2 is true\n"
					+ "     endIf:\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation.equals("*" ) ){

				answer += "     mult $t0, $t1 #operation result in $LO\n"
					+ "     mflo $t2 #result in $t2\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation.equals("/" ) ){

				answer += "     div $t0, $t1 #operation result in $LO\n"
					+ "     mflo $t2 #result in $t2\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation.equals("mod" ) ){

				answer += "     div $t0, $t1 #operation result in $LO\n"
					+ "     mfhi $t2 #result in $t2\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation.equals("and" ) ){

				answer += "     beq $t0, $zero, fail\n"
					+ "     beq $t1, $zero, fail\n"
					+ "     li $t2, 1 #$t2 is true\n"
					+ "     j endIf\n"
					+ "     fail:\n"
					+ "     li $t2, 0 #$t2 is false\n"
					+ "     endIf:\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation.equals("=" ) ){

				answer += "     bne $t0, $t1, notEqual\n"
					+ "     li $t2, 1 #$t2 is true\n"
					+ "     j endIf\n"
					+ "     notEqual:\n"
					+ "     li $t2, 0 #$t2 is false\n"
					+ "     endIf:\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation.equals("<=" ) ){

				answer += "     bne $t0, $t1, notEqual\n"
					+ "     li $t2, 1 #$t2 is true\n"
					+ "     j endIf\n"
					+ "     notEqual:\n"
					+ "     slt $t2, $t0, $t1 \n"
					+ "     endIf:\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation.equals(">=" ) ){

				answer += "     bne $t0, $t1, notEqual\n"
					+ "     li $t2, 1 #$t2 is true\n"
					+ "     j endIf\n"
					+ "     notEqual:\n"
					+ "     slt $t2, $t1, $t0 \n"
					+ "     endIf:\n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation.equals("<" ) ){

				answer += "     slt $t2, $t0, $t1 \n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}else if( operation.equals(">" ) ){

				answer += "     slt $t2, $t1, $t0 \n"
					+ "     sw $t2, ($sp) #put on stack\n";

			}

		}

		answer += "     #end OperationExpressionNode\n";

		return answer;

	}

}


