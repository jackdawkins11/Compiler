
package syntaxTree;

import variableType.*;

/*
 * This represents
 * an assignment
 * statement.
 */

public class VariableAssignmentStatementNode extends StatementNode{

	//////////////////
	//     Data     //
	//////////////////

	private VariableNode variable;

	private ExpressionNode arrayOffset;

	private ExpressionNode rValue;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public VariableAssignmentStatementNode( VariableNode variableTmp,
			ExpressionNode arrayOffsetTmp,
			ExpressionNode rValueTmp ){

		variable = variableTmp;

		arrayOffset = arrayOffsetTmp;

		rValue = rValueTmp;

	}

	public VariableAssignmentStatementNode( VariableNode variableTmp,
			ExpressionNode rValueTmp ){

		variable = variableTmp;

		arrayOffset = null;

		rValue = rValueTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	@Override
	public String indentedToString( int level ){

		if( arrayOffset != null ){

			String answer = indentation( level )
				+ "VariableAssignmentStatementNode.\n"
				+ arrayOffset.indentedToString( level + 1 )
				+ variable.indentedToString( level + 1 )
				+ rValue.indentedToString( level + 1 );

			return answer;

		}else{

			String answer = indentation( level )
				+ "VariableAssignmentStatementNode.\n"
				+ variable.indentedToString( level + 1 )
				+ rValue.indentedToString( level + 1 );

			return answer;

		}

	}

	@Override
	public String toMips(){

		String answer = "     #VariableAssignmentStatementNode\n";

		if( variable.isArray() ){
		
			answer += arrayOffset.toMips()
				+ rValue.toMips()
				+ "     lw $t0, 4($sp) #$t0 is array index\n"
				+ "     add $t0, $t0, $t0\n"
				+ "     add $t0, $t0, $t0\n"
				+ "     la $t1, " + variable.getName() + " #$t1 is array start\n"
				+ "     add $t0, $t1, $t0 # $t0 is value\n"
				+ "     lw $t1, ($sp) #$t1 is value\n"
				+ "     addi $sp, $sp, 8 #pop stack\n"
				+ "     sw $t1, ($t0) #set array\n";
	
		}else{

			if( variable.getStandardType() == EnumStandardType.REAL ){

				answer += rValue.toMips()
					+ "     la $t0, " + variable.getName() + " #$t0 is variable address\n"
					+ "     lwc1 $f1, ($sp) #$t1 is value\n"
					+ "     addi $sp, $sp, 4 #pop stack\n"
					+ "     swc1 $f1, ($t0) #set var\n";

			}else{	

				answer += rValue.toMips()
					+ "     la $t0, " + variable.getName() + " #$t0 is variable address\n"
					+ "     lw $t1, ($sp) #$t1 is value\n"
					+ "     addi $sp, $sp, 4 #pop stack\n"
					+ "     sw $t1, ($t0) #set var\n";
	
			}

		}

		answer += "     #end VariableAssignmentStatementNode\n";

		return answer;

	}

}
