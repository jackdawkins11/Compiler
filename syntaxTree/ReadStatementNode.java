
package syntaxTree;

public class ReadStatementNode extends StatementNode {

	String readId;

	public ReadStatementNode( String readIdTmp ){

		readId = readIdTmp;

	}
	
	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "ReadStatementNode. String:" + readId + "\n";

		return answer;

	}

}


