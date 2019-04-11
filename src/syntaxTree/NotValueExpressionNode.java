
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

	public NotValueExpressionNode( ExpressionNode expressionTmp ) throws Exception {

		expression = expressionTmp;

		if( expression.getStandardType() == EnumStandardType.REAL ){

			throw new Exception( "Cannot negate floating point expression." );

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
	public String toMips( String indent ){

		String answer = indent + "#NotValueExpressionNode\n"
			  + expression.toMips( indent )
			  + indent + "lw $t0, ($sp) #put expression into $t0\n"
			  + indent + "beq $t0, $zero, Else #go to else if $t0 is 0\n"
			  + indent + "li $t0, 1 #true expression\n"
			  + indent + "sw $t0, ($sp) #save on stack\n"
			  + indent + "j Endif\n"
			  + indent + "Else: \n"
			  + indent + "li $t0, 0 #false expression\n"
			  + indent + "sw $t0, ($sp) #save on stack\n"
			  + indent + "Endif:\n"
			  + indent + "#end NotValueExpressionNode\n";

		return answer;

	}

}


