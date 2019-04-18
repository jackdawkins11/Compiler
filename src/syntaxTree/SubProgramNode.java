
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

	DeclarationsNode variables, arguments;

	CompoundStatementNode functionBody;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public SubProgramNode( String nameTmp,
			EnumStandardType returnTypeTmp,
			DeclarationsNode variablesTmp,
			DeclarationsNode argumentsTmp,
			CompoundStatementNode functionBodyTmp ){

		name = nameTmp;

		returnType = returnTypeTmp;

		variables = variablesTmp;
		
		arguments = argumentsTmp;

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
			+ arguments.indentedToString( level + 1 )
			+ functionBody.indentedToString( level + 1 );

		return answer;
	}

	public String mipsDeclareVariables(){
		
		String answer = "#SubProgramNode Name: " + name + "\n";

		answer += variables.mipsDeclareVariables()
			+ arguments.mipsDeclareVariables();

		answer += "#end SubProgramNode\n";	
		
		return answer;

	}
	
	public String mipsDeclareFunctions(){
		
		String answer = "#SubProgramNode\n"
			      + name + ":\n"
			      + arguments.mipsInitFromStack()
			      + functionBody.toMips()
			      + "jr $ra\n"
			      + "#end SubProgramNode\n";

		return answer;

	}

}
