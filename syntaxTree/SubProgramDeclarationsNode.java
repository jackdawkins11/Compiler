
package syntaxTree;

import java.util.ArrayList;

public class SubProgramDeclarationsNode extends SyntaxTreeNode{

	private ArrayList< SubProgramNode > procs___ = new ArrayList< SubProgramNode >();

	public void addSubProgramDeclaration( SubProgramNode aSubProgram ){ procs___.add( aSubProgram ); }

	@Override
	public String indentedToString( int level ){
		
		String answer = indentation( level );

		answer += "SubProgramDeclarations\n";

		for( SubProgramNode subProg : procs___ ){
			
			answer += subProg.indentedToString( level + 1 );

		}

		return answer;

	}

}


