
package syntaxTree;

import java.util.ArrayList;

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
			ArrayList< ExpressionNode > parametersTmp ){

		subProgram = subProgramTmp;

		parameters = parametersTmp;

	}

	public SubProgramValueExpressionNode( SubProgramNode subProgramTmp){

		subProgram = subProgramTmp;

		parameters = new ArrayList< ExpressionNode >();

	}

	/////////////////////////////
	//     Public Programs     //
	/////////////////////////////

	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "SubProgramValueExpressionNode."
			+ " Name: " + subProgram.getName()
			+ "\n";
		
		for( int i = 0; i < parameters.size(); i++){

			answer += parameters.get( i ).indentedToString( level + 1 );

		}

		return answer;

	}
}


