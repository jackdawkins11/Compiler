
package syntaxTree;

import java.util.ArrayList;

/*
 * A DeclarationsNode is
 * just an array of VariableNodes.
 */

public class DeclarationsNode extends SyntaxTreeNode {

	//////////////////
	//     Data     //
	//////////////////

	private ArrayList< VariableNode > variables = new ArrayList< VariableNode >();

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	public void addVariable( VariableNode newVariable ){

		variables.add( newVariable );

	}

	public String indentedToString( int level ){

		String answer = indentation( level )
		       + "DeclarationsNode."
		       + "\n";

		for( VariableNode variable : variables ){

			answer += variable.indentedToString( level + 1 );

		}

		return answer;

	}

	public String mipsDeclareVariables( String indent,
			RegisterInfo integerRegisterInfo,
			RegisterInfo fpRegisterInfo ){

		String answer = indent + "#DeclarationsNode\n";
		
		for( VariableNode variable : variables ){

			if( variable.getType()
					== EnumStandardType.REAL ){
	
				answer += variable.toMips( indent, fpRegisterInfo );

			}else{

				answer += variable.toMips( indent, integerRegisterInfo );

			}

		}
		
		answer += indent + "#end DeclarationsNode\n";

		return answer;

	}

	public String mipsInitFromStack(){

		String answer = "#DeclarationsNode being set from stack.\n";

		answer += "addi $sp, $sp, "
			+ String.valueOf( 4 * variables.size() ) + "#pop stack \n";

		for( int i = 0; i < variables.size(); i++ ){

			VariableNode variable = variables.get( i );

			answer += "lw " + variable.getRegisterName() + ", "
				+ String.valueOf( 4 * i ) + "($sp) #load from stack\n";

		}
		
		answer += "#end DeclarationsNode being set from stack\n";

		return answer;

	}

}
