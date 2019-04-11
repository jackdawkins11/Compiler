
package syntaxTree;

/*
 * A statement
 * that reads
 * to a variable.
 */

public class ReadStatementNode extends StatementNode {

	//////////////////
	//     Data     //
	//////////////////

	String readId;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public ReadStatementNode( String readIdTmp ){

		readId = readIdTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////
	
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "ReadStatementNode."
			+ " String: " + readId
			+ "\n";

		return answer;

	}

	@Override
	public String toMips( String indent ){

		String answer = indent + "#ReadStatementNode\n";

		answer += indent + "#end ReadStatementNode\n";

		return answer;

	}
}


