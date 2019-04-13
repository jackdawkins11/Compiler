
package syntaxTree;

import java.util.ArrayList;
import variableType.EnumStandardType;

/*
 * A subProgram to be evaluated
 * and then used as a
 * factor in an
 * expression.
 */

public class SubProgramValueExpressionNode extends ExpressionNode {

	//////////////////
	//     Data     //
	//////////////////
	
	private SubProgramNode subProgram;

	private ArrayList< ExpressionNode > parameters;

	//////////////////////////
	//     Constructors     //
	//////////////////////////

	public SubProgramValueExpressionNode( SubProgramNode subProgramTmp,
			ArrayList< ExpressionNode > parametersTmp){

		subProgram = subProgramTmp;

		parameters = parametersTmp;

	}

	public SubProgramValueExpressionNode( SubProgramNode subProgramTmp){

		subProgram = subProgramTmp;

		parameters = new ArrayList< ExpressionNode >();

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	@Override
	public EnumStandardType getStandardType(){

		return subProgram.getStandardType();

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "SubProgramValueExpressionNode."
			+ " Name: " + subProgram.getName()
			+ " Standard Type: " + getStandardType().toString()
			+ "\n";
		
		for( int i = 0; i < parameters.size(); i++){

			answer += parameters.get( i ).indentedToString( level + 1 );

		}

		return answer;

	}

	@Override
	public String toMips(){

		String answer = "     #SubProgramValueExpressionNode\n";

		for( int i =0 ; i < parameters.size(); i++){

			answer += parameters.get( i ).toMips();
		
		}

		answer += "     jal " + subProgram.getName() + "\n";

		if( subProgram.getStandardType() == EnumStandardType.REAL ){

			answer += "     addi $sp, $sp, -4 #make room on stack\n"
				+ "     lw $f0, ($sp) #put on stack\n";

		}else{

			answer += "     addi $sp, $sp, -4 #make room on stack\n"
				+ "     lw $v0, ($sp) #put on stack\n";

		}

		answer += "     #end SubProgramValueExpressionNode\n";

		return answer;

	}
}


