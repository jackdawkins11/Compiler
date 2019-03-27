
package syntaxTree;

import java.util.ArrayList;

public class SubProgramDeclarationsNode extends SyntaxTreeNode{

	ArrayList< SubProgramNode > subPrograms = new ArrayList< SubProgramNode >();

	public void addSubProgram( SubProgramNode subProgramNode ){

		subPrograms.add( subProgramNode );

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "SubProgramDeclarationsNode.\n";

		for( int i = 0; i < subPrograms.size(); i++){

			answer += subPrograms.get( i ).indentedToString( level + 1 );

		}

		return answer;

	}

}
