
package syntaxTree;

public class ProgramNode extends SyntaxTreeNode {

	private String name;
	private DeclarationsNode variables;
	private SubProgramDeclarationsNode functions;
	private CompoundStatementNode main;

	public ProgramNode( String aName ){ name = aName; }

	public DeclarationsNode getVariables(){ return variables; }

	public SubProgramDeclarationsNode getFunctions(){ return functions; }

	public CompoundStatementNode getMain(){ return main; }

	public void setVariables( DeclarationsNode variablesTmp ){ variables = variablesTmp; }

	public void setFunctions( SubProgramDeclarationsNode functionsTmp ){ functions = functionsTmp; }

	public void setMain( CompoundStatementNode mainTmp ){ main = mainTmp; }

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "Program: " + name + "\n";

		answer += variables.indentedToString( level + 1 );

		answer += functions.indentedToString( level + 1 );

		answer += main.indentedToString( level + 1 );

		return answer;

	}

}


