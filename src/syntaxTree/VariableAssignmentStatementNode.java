
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

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "VariableAssignmentStatementNode.\n"
			+ arrayOffset.indentedToString( level + 1 )
			+ variable.indentedToString( level + 1 )
			+ rValue.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips(){

		String answer = "     #VariableAssignmentStatementNode\n";

		if( variable.isArray() ){
		
			answer += arrayOffset.toMips()
				+ "     lw $t0, ($sp) #$t0 is array index\n"
				+ "     addi $sp, $sp, 4 #pop stack\n"
				+ "     add $t0, $t0, $t0\n"
				+ "     add $t0, $t0, $t0\n"
				+ "     lw $t1, " + variable.getName() + " #$t1 is array start\n"
				+ "     add $t0, $t1, $t0 # $t0 is value\n"
				+ rValue.toMips()
				+ "     lw $t1, ($sp) #$t1 is value\n"
				+ "     sw $t1, ($t0) #set array\n";
	
		}else{

			answer += "     la $t0, " + variable.getName() + " #$t0 is variable address\n"
				+ rValue.toMips()
				+ "     lw $t1, ($sp) #$t1 is value\n"
				+ "     sw $t1, ($t0) #set var\n";
	
		}

		answer += "     #end VariableAssignmentStatementNode\n";

		return answer;

	}

}
