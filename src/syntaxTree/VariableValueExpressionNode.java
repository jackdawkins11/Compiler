
package syntaxTree;

import variableType.EnumStandardType;
/*
 * A variable
 * being used as
 * a factor.
 */

public class VariableValueExpressionNode extends ExpressionNode {

	//////////////////
	//     Data     //
	//////////////////

	private VariableNode variable;

	private ExpressionNode arrayIndex;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public VariableValueExpressionNode( VariableNode variableTmp, ExpressionNode arrayIndexTmp ) throws RuntimeException {

		if( arrayIndex.getStandardType() != EnumStandardType.INTEGER ){

			throw new RuntimeException( "cannot access array without integer type" );

		}

		variable = variableTmp;

		arrayIndex = arrayIndexTmp;

	}

	public VariableValueExpressionNode( VariableNode variableTmp ){

		variable = variableTmp;

		arrayIndex = null;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	@Override
	public EnumStandardType getStandardType(){

		return variable.getStandardType();

	}

	@Override
	public String indentedToString( int level ){

		String answer = null;

		if( arrayIndex != null ){

			answer = indentation( level )
				+ "VariableValueExpressionNode."
				+ " Standard Type: " + getStandardType().toString()
				+ "\n"
				+ arrayIndex.indentedToString( level + 1 )
				+ variable.indentedToString( level + 1 );

		}else{

			answer = indentation( level )
				+ "VariableValueExpressionNode."
				+ " Standard Type: " + getStandardType().toString()
				+ "\n"
				+ arrayIndex.indentedToString( level + 1 )
				+ variable.indentedToString( level + 1 );

		}

		return answer;

	}

	@Override
	public String toMips(){

		String answer = "     #VariableValueExpressionNode\n";

		if( arrayIndex != null ){
		
			answer += arrayIndex.toMips()		//array index on stack
				+ "     lw $t0, ($sp) #$t0 is array index\n"
				+ "	addi $sp, $sp, 4 $pop stack\n"
				+ "     add $t0, $t0, $t0\n"
				+ "     add $t0, $t0, $t0\n"
				+ "     la $t1, " + variable.getName() + " #$t1 is array start\n"
				+ "     add $t0, $t1, $t0 # $t0 is variable location\n";

		}else{

			answer += "     la $t0, " + variable.getName() + " #$t0 is variable address\n";
		
		}
		
		if( variable.getStandardType() == EnumStandardType.REAL ){

			answer += "lwc1 $f0, ($t0) #val in $f1\n"
				+ "addi $sp, $sp, -4 #make room on stack\n"
				+ "swc1 $f0, ($sp) #save to stack\n";

		}else{

			answer += "lw $t1, ($t0) #val in $t1\n"
				+ "addi $sp, $sp, -4 #make room on stack\n"
				+ "sw $t1, ($sp) $save to stack\n";

		}

		answer += "     #end VariableValueExpressionNode\n";

		return answer;

	}



}


