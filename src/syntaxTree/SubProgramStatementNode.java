
package syntaxTree;

import java.util.ArrayList;
import variableType.*;

/*
 * A statement
 * calling a subprogram.
 */

public class SubProgramStatementNode extends StatementNode {

	//////////////////
	//     Data     //
	//////////////////

	private SubProgramNode subProgram;

	private ArrayList< ExpressionNode > parameters;

	//////////////////////////
	//     Constructors     //
	//////////////////////////

	public SubProgramStatementNode( SubProgramNode subProgramTmp,
			ArrayList< ExpressionNode > parametersTmp ){

		subProgram = subProgramTmp;

		parameters = parametersTmp;

	}

	public SubProgramStatementNode( SubProgramNode subProgramTmp ){

		subProgram = subProgramTmp;

		parameters = new ArrayList< ExpressionNode >();

	}

	/////////////////////////////
	//     Public Programs     //
	/////////////////////////////

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "SubProgramStatementNode."
			+ " Name: " + subProgram.getName()
			+ "\n";
		
		for( int i = 0; i < parameters.size(); i++){

			answer += parameters.get( i ).indentedToString( level + 1 );

		}

		return answer;

	}

	@Override
	public String toMips(){

		String answer = "     #SubProgramStatementNode\n";

		answer += "     addi $sp, $sp, -4\n"
			+ "     sw $ra, ($sp) #save $ra\n";

		for( int i =0 ; i < parameters.size(); i++){

			answer += parameters.get( i ).toMips();

		}

		answer += "     jal " + subProgram.getName() + "\n";
	
		answer += "     lw $ra, ($sp) #restore $ra\n"
			+ "     addi $sp, $sp, 4\n";

		answer += "     #end SubProgramStatementNode\n";

		return answer;

	}

}


