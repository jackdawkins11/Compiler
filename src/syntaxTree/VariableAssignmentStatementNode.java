
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
			ExpressionNode rValueTmp ) throws RuntimeException {

		if( arrayOffsetTmp.getStandardType() != EnumStandardType.INTEGER ){

			throw new RuntimeException( "Cannot index array with f.p." );

		}

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

		answer += rValue.toMips();	//expression on stack
		
		if( arrayOffset != null ){
		
			answer += arrayOffset.toMips()		//array index on stack
				+ "     lw $t0, ($sp) #$t0 is array index\n"
				+ "	addi $sp, $sp, 4 #pop stack\n"
				+ "     add $t0, $t0, $t0\n"
				+ "     add $t0, $t0, $t0\n"
				+ "     la $t1, " + variable.getName() + " #$t1 is array start\n"
				+ "     add $t0, $t1, $t0 # $t0 is variable location\n";

		}else{

			answer += "     la $t0, " + variable.getName() + " #$t0 is variable address\n";
		
		}
		
		if( rValue.getStandardType() == EnumStandardType.REAL 
				&& variable.getStandardType() == EnumStandardType.REAL ){

			answer += "     lwc1 $f1, ($sp) #$f1 is value\n"
				+ "     addi $sp, $sp, 4 #pop stack\n"
				+ "     swc1 $f1, ($t0) #set var\n";

		}else if( rValue.getStandardType() == EnumStandardType.REAL
				&& variable.getStandardType() == EnumStandardType.INTEGER ){

			answer += "     lw $t1, ($sp) #$t1 is value\n"
				+ "     addi $sp, $sp, 4 #pop stack\n"
				+ "     cvt.w.s $t1, $t1 #convert to integer\n"
				+ "     swc1 $t1, ($t0) #set var\n";

		}else if( rValue.getStandardType() == EnumStandardType.INTEGER 
				&& variable.getStandardType() == EnumStandardType.INTEGER ){

			answer += "     lw $t1, ($sp) #$t1 is value\n"
				+ "     addi $sp, $sp, 4 #pop stack\n"
				+ "     sw $t1, ($t0) #set var\n";

		}else if( rValue.getStandardType() == EnumStandardType.INTEGER 
				&& variable.getStandardType() == EnumStandardType.REAL ){

			answer += "     lwc1 $f1, ($sp) #$t1 is value\n"
				+ "     addi $sp, $sp, 4 #pop stack\n"
				+ "     cvt.s.w $f1, $f1 #convert to real\n"
				+ "     swc1 $f1, ($t0) #set var\n";

		}

		answer += "     #end VariableAssignmentStatementNode\n";

		return answer;

	}

}
