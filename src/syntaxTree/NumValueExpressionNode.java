
package syntaxTree;

import variableType.EnumStandardType;

/*
 * A number.
 */

public class NumValueExpressionNode extends ExpressionNode {

	//////////////////
	//     Data     //
	//////////////////

	EnumStandardType standardType;

	String numString;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public NumValueExpressionNode( String numStringTmp ){

		numString = numStringTmp;

		standardType = EnumStandardType.INTEGER;

		for( int i = 0 ; i < numString.length(); i++){

			if( numString.charAt( i ) == '.' ){

				standardType = EnumStandardType.REAL;

			}

		}

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	@Override
	public EnumStandardType getStandardType(){

		return standardType;

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "NumValueExpressionNode."
			+ " Num: " + numString
			+ " Standard Type: " + getStandardType().toString()
			+ "\n";

		return answer;

	}
}


