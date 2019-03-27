
package syntaxTree;

public class ProgramNode extends SyntaxTreeNode {

	private String name;
	private DeclarationsNode variables;
	private SubProgramDeclarationsNode functions;
	private CompoundStatementNode main;

	public ProgramNode( String nameTmp, DeclarationsNode variablesTmp,
		SubProgramDeclarationsNode functionsTmp, CompoundStatementNode mainTmp ){

		name = nameTmp;

		variables = variablesTmp;

		functions = functionsTmp;

		main = mainTmp;

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "ProgramNode. Name: " + name + "\n";

		answer += variables.indentedToString( level + 1 );

		answer += functions.indentedToString( level + 1 );

		answer += main.indentedToString( level + 1 );

		return answer;

	}

}


