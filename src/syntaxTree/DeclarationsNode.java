
package syntaxTree;

import variableType.*;
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

	public String mipsDeclareVariables(){

		String answer = "#DeclarationsNode\n";
		
		for( VariableNode variable : variables ){

			if( variable.isArray() ){
	
				answer += variable.getName() + ": .space "
					+ String.valueOf( variable.getSize() ) + "\n";
			
			}else if( variable.getStandardType() == EnumStandardType.REAL ){
	
				answer += variable.getName() + ": .float 0.0 \n";

			}else if( variable.getStandardType() == EnumStandardType.INTEGER ){
	
				answer += variable.getName() + ": .word 0 \n";

			}

		}
		
		answer += "#end DeclarationsNode\n";

		return answer;

	}

	public String mipsInitFromStack(){

		String answer = "     #DeclarationsNode being set from stack.\n";

		answer += "     addi $sp, $sp, "
			+ "     " + String.valueOf( 4 * variables.size() ) + " #pop stack \n";

		for( int i = 0; i < variables.size(); i++ ){

			VariableNode variable = variables.get( i );
			
			answer += "     lw $t0, -" + String.valueOf( 4 * i ) + "($sp) #argument in $t0\n"
				+ "     la $t1, " + variable.getName() + " #$t1 is location of var\n"
				+ "     sw $t0, ($t1) #set variable\n";
		
		}
		
		answer += "     #end DeclarationsNode being set from stack\n";

		return answer;

	}

}
