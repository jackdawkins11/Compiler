
package syntaxTree;

import variableType.*;

/*
 * A SubProgramNode
 * represents a function.
 * A void return type
 * signifies a procedure.
 */

public class SubProgramNode extends SyntaxTreeNode {

	//////////////////
	//     Data     //
	//////////////////

	String name;

	VariableType returnType;

	DeclarationsNode variables;

	CompoundStatementNode functionBody;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public SubProgramNode( String nameTmp,
			VariableType returnTypeTmp,
			DeclarationsNode variablesTmp,
			CompoundStatementNode functionBodyTmp ){

		name = nameTmp;

		returnType = returnTypeTmp;

		variables = variablesTmp;

		functionBody = functionBodyTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	public String getName(){

		return name;

	}

	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "SubProgramNode."
			+ " Name: " + name
			+ "\n"
			+ variables.indentedToString( level + 1 )
			+ functionBody.indentedToString( level + 1 );

		return answer;
	}

}
