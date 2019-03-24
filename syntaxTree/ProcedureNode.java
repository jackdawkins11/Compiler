
package syntaxTree;

public class ProcedureNode extends SubProgramNode {

	private String name;
	private DeclarationsNode parameters, variables;
	private CompoundStatementNode main;

	public ProcedureNode( String nameTmp, DeclarationsNode parametersTmp,
			DeclarationsNode variablesTmp,
			CompoundStatementNode mainTmp ){

		name = nameTmp;

		parameters = parametersTmp;

		variables = variablesTmp;

		main = mainTmp;

	}

	@Override
	public String getName(){

		return name;

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "ProcedureNode. Name: " + name + "\n";

		answer += parameters.indentedToString( level + 1 );

		answer += variables.indentedToString( level + 1 );
		
		answer += main.indentedToString( level + 1 );

		return answer;

	}

}


