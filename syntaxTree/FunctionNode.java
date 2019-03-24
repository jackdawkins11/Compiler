
package syntaxTree;

import variableType.VariableType;

public class FunctionNode extends SubProgramNode {

	private String name;
	private DeclarationsNode parameters, variables;
	private VariableType returnType;
	private CompoundStatementNode main;

	public FunctionNode( String nameTmp, DeclarationsNode parametersTmp,
			VariableType returnTypeTmp,
			DeclarationsNode variablesTmp,
			CompoundStatementNode mainTmp ){

		name = nameTmp;

		parameters = parametersTmp;

		returnType = returnTypeTmp;

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

		answer += "FunctionNode. Name: " + name + "\n";

		answer += parameters.indentedToString( level + 1 );
		
		answer += variables.indentedToString( level + 1 );

		answer += main.indentedToString( level + 1 );

		return answer;

	}

}


