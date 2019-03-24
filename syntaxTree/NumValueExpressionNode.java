
package syntaxTree;

public class NumValueExpressionNode extends ExpressionNode {

	String numString;

	public NumValueExpressionNode( String numStringTmp ){

		numString = numStringTmp;

	}

	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "NumValueExpressionNode. Num: " + numString + "\n";

		return answer;

	}
}


