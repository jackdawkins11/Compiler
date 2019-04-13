
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

	public String toMips(){

		//function variables
			
		RegisterInfo integerRegisterInfo = new RegisterInfo( 4, 7, "$s" );
		
		RegisterInfo fpRegisterInfo = new RegisterInfo( 26, 31, "$f" );

		String answer = "#SubProgramNode\n"
			      + name + ":\n"
			      + variables.mipsDeclareVariables( "     ",
					      integerRegisterInfo,
					      fpRegisterInfo)
			      + arguments.mipsDeclareVariables( "     ",
					     integerRegisterInfo,
					     fpRegisterInfo )
			      + arguments.mipsInitFromStack()
			      + functionBody.toMips( "     " )
			      + "#end SubProgramNode\n";

		return answer;

	}

}
