
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

	public VariableValueExpressionNode( VariableNode variableTmp, ExpressionNode arrayIndexTmp ){

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

		if( variable.isArray() ){
		
			answer += arrayIndex.toMips()
				+ "     lw $t0, ($sp) #$t0 is array index\n"
				+ "     add $t0, $t0, $t0\n"
				+ "     add $t0, $t0, $t0\n"
				+ "     lw $t1, " + variable.getName() + " #$t1 is array start\n"
				+ "     add $t0, $t1, $t0 # $t0 is value\n"
				+ "     lw $t1, ($t0) #$t1 is value\n"
				+ "     sw $t1, ($sp) #add to stack\n";
	
		}else{

			answer += "     lw $t0, " + variable.getName() + " #$t0 is value\n"
				+ "     addi $sp, $sp, -4 #make room on stack\n"
				+ "     sw $t0, ($sp) #add to stack\n";
	
		}

		answer += "     #end VariableValueExpressionNode\n";

		return answer;

	}



}


