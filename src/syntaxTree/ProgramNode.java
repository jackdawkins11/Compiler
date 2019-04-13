
package syntaxTree;

/*
 * A ProgramNode
 * is the entire
 * program.
 */

public class ProgramNode extends SyntaxTreeNode {

	//////////////////
	//     Data     //
	//////////////////

	private String name;

	private DeclarationsNode variables;

	private SubProgramDeclarationsNode functions;

	private CompoundStatementNode main;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public ProgramNode( String nameTmp,
			DeclarationsNode variablesTmp,
			SubProgramDeclarationsNode functionsTmp,
			CompoundStatementNode mainTmp ){

		name = nameTmp;

		variables = variablesTmp;

		functions = functionsTmp;

		main = mainTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "ProgramNode." 
			+ " Name: " + name 
			+ "\n"
			+ variables.indentedToString( level + 1 )
			+ functions.indentedToString( level + 1 )
			+ main.indentedToString( level + 1 );

		return answer;

	}

	public String toMips(){

		String answer = "#ProgramNode " + name + "\n";

		answer += ".text\n"
			+ "main:\n";

		//global variables
		
		RegisterInfo integerRegisterInfo = new RegisterInfo( 0, 3, "$s" );
		
		RegisterInfo fpRegisterInfo = new RegisterInfo( 20, 25, "$f" );

		answer += variables.mipsDeclareVariables( "     ",
				integerRegisterInfo,
				fpRegisterInfo );

		answer += main.toMips( "     " );

		answer += functions.toMips();

		answer += "#end ProgramNode\n";

		return answer;

	}

}


