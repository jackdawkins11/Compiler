
package syntaxTree;

import variableType.EnumStandardType;

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

	EnumStandardType returnType;

	DeclarationsNode variables;

	CompoundStatementNode functionBody;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public SubProgramNode( String nameTmp,
			EnumStandardType returnTypeTmp,
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

	public EnumStandardType getStandardType(){

		return returnType;

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

	public String toMips(){
	
		String answer = "#SubProgramNode\n"
			      + name + ":\n"
			      + variables.toMips( "     " )
			      + functionBody.toMips( "     " );

		return answer;

	}



}
