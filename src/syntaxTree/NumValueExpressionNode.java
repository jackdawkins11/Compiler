
package syntaxTree;

/*
 * A number.
 */

public class NumValueExpressionNode extends ExpressionNode {

	//////////////////
	//     Data     //
	//////////////////

	String numString;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public NumValueExpressionNode( String numStringTmp ){

		numString = numStringTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "NumValueExpressionNode."
			+ " Num: " + numString 
			+ "\n";

		return answer;

	}
}


