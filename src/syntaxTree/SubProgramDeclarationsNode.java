
package syntaxTree;

import java.util.ArrayList;

/*
 * An array
 * of SubProgramNodes.
 */

public class SubProgramDeclarationsNode extends SyntaxTreeNode{

	//////////////////
	//     Data     //
	//////////////////

	ArrayList< SubProgramNode > subPrograms = new ArrayList< SubProgramNode >();

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public void addSubProgram( SubProgramNode subProgramNode ){

		subPrograms.add( subProgramNode );

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "SubProgramDeclarationsNode."
			+ "\n";

		for( int i = 0; i < subPrograms.size(); i++){

			answer += subPrograms.get( i ).indentedToString( level + 1 );

		}

		return answer;

	}

	public String mipsDeclareVariables(){

		String answer = "#SubProgramDeclarationsNode\n";

		for( SubProgramNode subProgramNode : subPrograms ){

			answer += subProgramNode.mipsDeclareVariables();

		}

		answer += "#end SubProgramDeclarationsNode\n";

		return answer;

	}

	public String mipsDeclareFunctions(){

		String answer = "#SubProgramDeclarationsNode\n";

		for( SubProgramNode subProgramNode : subPrograms ){

			answer += subProgramNode.mipsDeclareFunctions();

		}

		answer += "#end SubProgramDeclarationsNode\n";

		return answer;

	}

}
