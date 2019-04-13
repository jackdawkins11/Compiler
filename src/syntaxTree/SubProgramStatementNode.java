
package syntaxTree;

import java.util.ArrayList;

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
			ArrayList< ExpressionNode > parametersTmp )
	throws exception {

		if( 2 < parametersTmp.getSize() ){

			throw new Exception( "too many parameters in function definition." );

		}

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
	public String toMips( String indent ){

		String answer = indent + "#SubProgramStatementNode\n";

		for( int i =0 ; i < parameters.size(); i++){

			if( parameters.get( i ).getStandardType()
					== EnumStandardType.REAL ){

				answer += parameters.get( i ).toMips( indent );

			}else{

				answer += parameters.get( i ).toMips( indent );

			}

		}

		answer += "jal " + subProgram.getName() + "\n";
		
		String answer += indent + "#end SubProgramStatementNode\n";

		return answer;

	}

}


