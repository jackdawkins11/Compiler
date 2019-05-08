
package syntaxTree;

import variableType.EnumStandardType;

/*
 * An expression
 * that is negated.
 */

public class NotValueExpressionNode extends ExpressionNode {

	//////////////////
	//     Data     //
	//////////////////

	ExpressionNode expression;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public NotValueExpressionNode( ExpressionNode expressionTmp ) throws RuntimeException {

		expression = expressionTmp;

		if( expression.getStandardType()
				== EnumStandardType.REAL ){

			throw new RuntimeException( "Cannot convert real to boolean." );

		}

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	@Override
	public EnumStandardType getStandardType(){

		return expression.getStandardType();

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "NotValueExpressionNode."
			+ " Standard Type: " + getStandardType().toString()
			+ "\n"
			+ expression.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips(){

		String answer =
			    "     #NotValueExpressionNode\n"
			  + expression.toMips()
			  + "     lw $t0, ($sp) #put expression into $t0\n"
			  + "     beq $t0, $zero, Else #go to else if $t0 is 0\n"
			  + "     li $t0, 1 #true expression\n"
			  + "     sw $t0, ($sp) #save on stack\n"
			  + "     j Endif\n"
			  + "     Else: \n"
			  + "     li $t0, 0 #false expression\n"
			  + "     sw $t0, ($sp) #save on stack\n"
			  + "     Endif:\n"
			  + "     #end NotValueExpressionNode\n";

		return answer;

	}

}


