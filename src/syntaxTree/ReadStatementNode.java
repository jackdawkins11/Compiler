
package syntaxTree;

import variableType.*;

/*
 * A statement
 * that reads
 * to a variable.
 */

public class ReadStatementNode extends StatementNode {

	//////////////////
	//     Data     //
	//////////////////

	private VariableNode variable;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public ReadStatementNode( VariableNode variableTmp ) throws Exception {

		variable = variableTmp;

		if( variable.isArray() ){

			throw new Exception( "Cannot call read with array." );

		}

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////
		
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "ReadStatementNode.\n"
			+ variable.indentedToString( level + 1 );

		return answer;

	}

	@Override
	public String toMips(){

		String answer = "     #ReadStatementNode\n";

		if( variable.getStandardType() == EnumStandardType.REAL ){

			answer += "     li $v0, 6 #read float is syscall 6\n"
				+ "     syscall\n"
				+ "     la $t0, " + variable.getName() + " #$t0 is location of var\n"
				+ "     sw $f0, ($t0) #set var\n";

		}else{

			answer += "     li $v0, 5 #read int is syscall 5\n"
				+ "     syscall\n"
				+ "     la $t0, " + variable.getName() + " #$t0 is location of var\n"
				+ "     sw $v0, ($t0) #set var\n";

		}

		answer += "     #end ReadStatementNode\n";

		return answer;

	}
}


