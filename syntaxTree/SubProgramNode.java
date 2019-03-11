
package syntaxTree;

public class SubProgramNode extends SyntaxTreeNode {

	String name;

	DeclarationsNode declarations;

	CompoundStatementNode main;

	public void setName( String nameTmp ){ name = nameTmp; }

	public void setDeclarations( DeclarationsNode declarationsTmp ){ declarations = declarationsTmp; }

	public void setMain( CompoundStatementNode mainTmp ){ main = mainTmp; }

	public String getName(){ return name; }

	public DeclarationsNode getDeclarations(){ return declarations; }

	public CompoundStatementNode getMain(){ return main; }

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "SubProgram: " + name + "\n";

		answer += declarations.indentedToString( level + 1 );

		answer += main.indentedToString( level + 1 );

		return answer;

	}

}


